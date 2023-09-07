document.addEventListener("DOMContentLoaded", function () {

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
            optionsButton.addEventListener('click', (event) => {
                event.stopPropagation();
                console.log("clicked");
                console.log(optionsButton)
                });

            videoListContainer.appendChild(videoCard);
        });
    } catch (error) {
        console.error(error);
    }
}

// Call the function to fetch and populate video information
fetchAndPopulateVideoInfo();

});