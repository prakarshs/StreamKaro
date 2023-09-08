document.addEventListener("DOMContentLoaded", function () {

    const fileInput = document.getElementById('file-input');
    const dropArea = document.getElementById('drop-area');
    const browseButton = document.getElementById('browse-button');
    const uploadButton = document.getElementById('upload-button');
    const uploadOption = document.getElementById('upload-option');
    const fileUploadContainer = document.getElementById('file-upload-container');

    fileUploadContainer.style.display = 'none';

    uploadOption.classList.add('hover-scale')
    uploadOption.addEventListener('click', function () {
        if (fileUploadContainer.style.display === 'block' || fileUploadContainer.style.display === '') {
            // If the container is currently displayed or has no inline style (default), hide it
            fileUploadContainer.style.display = 'none';
        } else {
            // If the container is currently hidden, display it
            fileUploadContainer.style.display = 'block';
        }
    });

    // Function to handle file selection
    function handleFileSelect(files) {
        // Handle selected files
        window.alert('File will be uploaded: '+files[0].name);
        // You can perform further actions here, such as displaying file names, etc.
    }

    // Add event listeners

    // Browse button click event
    browseButton.addEventListener('click', function () {
        fileInput.click(); // Trigger the file input click
    });

    // File input change event
    fileInput.addEventListener('change', function () {
        const selectedFiles = fileInput.files;
        handleFileSelect(selectedFiles);
    });

    // Upload button click event
    uploadButton.addEventListener('click', function () {
        const selectedFiles = fileInput.files;

        if (selectedFiles.length > 0) {
            // Create a FormData object to send the files
            const formData = new FormData();
            formData.append('file', selectedFiles[0]); // Assuming only one file is selected

            // Send the formData to the API endpoint using fetch or XMLHttpRequest
            fetch('http://localhost:8082/streamKaro/upload', {
                method: 'POST',
                body: formData,
            })
                .then(response => response.json())
                .then(data => {

                    // Handle the response from the server
                    console.log(data);
                    // You can display a success message or perform further actions here
                })
                .catch(error => {
                    window.alert('File has been uploaded!!');
                    console.error(error);
                    // Handle any errors that occur during the upload
                });
        }
    });

// Function to fetch video information and populate the container
async function fetchAndPopulateVideoInfo() {
    try {
        const response = await fetch('http://localhost:8082/streamKaro/listVideos'); // Replace with your API endpoint
        if (!response.ok) {
            throw new Error('Failed to fetch video information');
        }
        const videoInfoList = await response.json();

        const videoListContainer = document.getElementById('videoListContainer');
        videoInfoList.forEach(videoInfo => {


         // Sanitize the videoInfo.Name to create a valid CSS class name
            const sanitizedClassName = videoInfo.Name.replace(/[^\w-]/g, '_');
            const fileName = videoInfo.Name;
            // Create a card-like element for each video
            const videoCard = document.createElement('div');
            videoCard.className = 'xl:w-1/3 md:w-1/2 p-4 center-content';

            videoCard.innerHTML = `
                <div class=" border border-gray-700 border-opacity-75 p-6 rounded-lg hover-scale-small">
                    <div class="options_${sanitizedClassName} w-10 h-10 inline-flex items-center justify-center rounded-full bg-gray-800 text-indigo-400 mb-4 ">
                     <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="#FF6347" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><line x1="4" y1="21" x2="4" y2="14"></line><line x1="4" y1="10" x2="4" y2="3"></line><line x1="12" y1="21" x2="12" y2="12"></line><line x1="12" y1="8" x2="12" y2="3"></line><line x1="20" y1="21" x2="20" y2="16"></line><line x1="20" y1="12" x2="20" y2="3"></line><line x1="1" y1="14" x2="7" y2="14"></line><line x1="9" y1="8" x2="15" y2="8"></line><line x1="17" y1="16" x2="23" y2="16"></line></svg>
                    </div>
                    <h2 class="text-lg text-white font-medium title-font mb-2">${videoInfo.Name}</h2>
                    <p class="leading-relaxed text-white font-medium text-base">${videoInfo.DateAdded}</p>
                </div>
            `;

            const optionsButton = videoCard.querySelector(`.options_${sanitizedClassName}`);
            optionsButton.classList.add('hover-scale');
            const optionsContainer = document.createElement('div');
            optionsContainer.className = '';
            optionsButton.addEventListener('click', (event) => {
                event.stopPropagation();
                console.log("clicked");
                toggleDropdown(optionsContainer,fileName);
                });

            optionsContainer.appendChild(optionsButton);

            // Append the parent container to the videoCard
            videoCard.appendChild(optionsContainer);
            videoListContainer.appendChild(videoCard);
        });

    } catch (error) {
        console.error(error);
    }
}
function toggleDropdown(optionsContainer,fileName) {
             const dropdownMenu = optionsContainer.querySelector('.dropdown');

                if (!dropdownMenu) {
                    // If the dropdown menu doesn't exist, create it
                    createDropdownMenu(optionsContainer,fileName);
                } else {
                    // If it already exists, toggle its visibility
                    dropdownMenu.style.display = (dropdownMenu.style.display === 'block') ? 'none' : 'block';
                }
            }

        // Function to create the dropdown menu
        function createDropdownMenu(optionsContainer,fileName) {
            const dropdownMenu = document.createElement('div');

            dropdownMenu.className = `dropdown fixed z-100 bg-gray border border-gray-700 rounded-md shadow-lg px-4 py-2`;
            console.log(dropdownMenu.className+"-"+fileName);
            dropdownMenu.innerHTML = `
                <ul>
                    <li id="dwnldId"><a href="#" class="download-option text-white hover-scale">Download</a></li>
                    <li id="dltId"><a href="#" class="delete-option text-white hover-scale">Delete</a></li>
                </ul>
            `;
            optionsContainer.appendChild(dropdownMenu);
            const downloadOption = dropdownMenu.querySelector('.download-option');
            const deleteOption = dropdownMenu.querySelector('.delete-option');

            downloadOption.addEventListener('click', function (event) {
                    event.preventDefault();
                    downloadFunction(fileName);
                });

                deleteOption.addEventListener('click', function (event) {
                    event.preventDefault();
                    deleteFunction(fileName);
                });


        }

// Function to call download video
async function downloadFunction(fileName) {
    try {
    console.log("in download function "+fileName);
        // Construct the API URL for download using fileName
        const downloadUrl = `http://localhost:8082/streamKaro/download/${fileName}`;
        const response = await fetch(downloadUrl);

        if (!response.ok) {
            throw new Error('Failed to download video');
        }

        // Convert the response to Blob
        const blob = await response.blob();

        // Create a temporary anchor element to trigger the download
        const a = document.createElement('a');
        a.href = window.URL.createObjectURL(blob);
        a.download = fileName;
        a.style.display = 'none';
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);

        window.alert('The video will be downloaded soon.');
    } catch (error) {
        console.error('Error downloading video:', error);
        }
}

// Function to delete a video
async function deleteFunction(fileName) {
    try {
    console.log("in delete function "+fileName);
        // Construct the API URL for delete using fileName
        const deleteUrl = `http://localhost:8082/streamKaro/delete/${fileName}`;
        const response = await fetch(deleteUrl, {
            method: 'DELETE',
        });

        if (!response.ok) {
            throw new Error('Failed to delete video');
        }
        window.alert('The video will be deleted soon.');

        // Handle the delete response here (e.g., update the UI)
    } catch (error) {
        console.error('Error deleting video:', error);
    }
}

// Call the function to fetch and populate video information
fetchAndPopulateVideoInfo();

});