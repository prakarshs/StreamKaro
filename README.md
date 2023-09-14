
<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/prakarshs/StreamKaro">
    <img src="FrontendService/src/main/resources/static/logo.png" alt="Logo" width="100" height="100">
  </a>

  <h3 align="center">StreamKaro! A Kafka Based Streaming Microservice</h3>

  <p align="center">
    Stream anything, anytime for free! Viewers require no signup!
    <br />

  </p>
</div>



<!-- ABOUT THE PROJECT -->
## About The Project

Welcome to our cutting-edge live streaming microservice, built on the powerful Spring Boot framework, and enriched with Okta security integration for streamer authentication. In this digital age of seamless connectivity, we bring you a revolutionary experience where creators can share their content effortlessly, and viewers can enjoy captivating live streams without the need for time-consuming sign-ups.

Our platform is designed to offer a diverse set of functionalities that cater to various user roles. At its core, we have harnessed the capabilities of Kafka to provide a dynamic, real-time streaming experience. The heart of our system lies within the Kafka broker, seamlessly connecting streamers and viewers.

Key Features:

**1. Streamer Security with Okta:** We prioritize the security of our content creators by integrating Okta authentication, ensuring that only authorized streamers can broadcast their content to our audience.

**2. Anonymous Viewer Experience:** For viewers, we've eliminated the barriers to entry. No registration or sign-up is required; simply tune in and enjoy the live streams.

**3. Kafka Based Real Time Streaming:** In our Kafka-based live streaming setup, the Streamer (Admin Service) Service acts as the producer, generating streaming packets from content creators. These packets are then transmitted to the Kafka broker, which efficiently distributes them. The Frontend Service, serving as the consumer, subscribes to relevant Kafka topics, enabling real-time updates and a seamless streaming experience for viewers. 

**4. Admin Control:** The content creators are administrators themselves and wield powerful tools, including the ability to upload, delete, and download videos. These videos are stored securely in our S3 bucket, forming the foundation of our live streams on the Kafka broker.

**5. Spring Eureka Service Discovery:** We leverage Spring Eureka for service discovery, ensuring that our microservices can easily locate and communicate with each other in a dynamic and scalable manner.

**6. Config Server for Streamlined Configuration:** Our configuration management is enhanced by Spring Config Server, allowing us to centralize and manage application configurations efficiently across our microservices architecture.

**5. Microservices Architecture:**

   - **AdminService:** This service empowers administrators to manage videos efficiently. You can effortlessly upload, delete, download, and list videos in our S3 bucket.
   
   - **FrontendService:** A user-friendly and visually engaging interface is at the forefront of our platform. Expect a seamless user experience, backed by thoughtful design and an intuitive UI.
   
   - **StreamService:** The core of our live streaming capability, this service utilizes Kafka to ensure smooth, real-time delivery of content from creators to viewers.

In a world where content creation and consumption are at the forefront of digital experiences, our Kafka-based live streaming microservice is poised to redefine how we connect and share. Whether you're a content creator, an eager viewer, or an administrator managing the flow of digital content, we invite you to embark on this journey with us. Welcome to the future of live streaming, where innovation meets simplicity.



### Built With

This section should list any major frameworks/libraries used to bootstrap your project. Leave any add-ons/plugins for the acknowledgements section. Here are a few examples.

* ![SpringBoot]
* ![JavaScript]
* ![AWS]
* ![Okta]
* ![Apache]



<!-- CONTACT -->
## Contact

Your Name - [@your_twitter](prakarsh2101@gmail.com)

Project Link: [https://github.com/prakarshs/StreamKaro](https://github.com/prakarshs/StreamKaro)

<p align="right">(<a href="#readme-top">back to top</a>)</p>





[contributors-shield]: https://img.shields.io/github/contributors/othneildrew/Best-README-Template.svg?style=for-the-badge
[contributors-url]: https://github.com/othneildrew/Best-README-Template/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/othneildrew/Best-README-Template.svg?style=for-the-badge
[forks-url]: https://github.com/othneildrew/Best-README-Template/network/members
[stars-shield]: https://img.shields.io/github/stars/othneildrew/Best-README-Template.svg?style=for-the-badge
[stars-url]: https://github.com/othneildrew/Best-README-Template/stargazers
[issues-shield]: https://img.shields.io/github/issues/othneildrew/Best-README-Template.svg?style=for-the-badge
[issues-url]: https://github.com/othneildrew/Best-README-Template/issues
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge
[license-url]: https://github.com/othneildrew/Best-README-Template/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[product-screenshot]: images/demo.gif
[SpringBoot]: https://img.shields.io/badge/SpringBoot-32CD32?style=for-the-badge&logo=springBoot&logoColor=white
[JavaScript]: https://img.shields.io/badge/JavaScript-FFEA00?style=for-the-badge&logo=javaScript&logoColor=black
[Docker]: https://img.shields.io/badge/Docker-0096FF?style=for-the-badge&logo=docker&logoColor=white
[Apache]: https://img.shields.io/badge/Apache%20Kafka-DE3163?style=for-the-badge&logo=apache&logoColor=white
[AWS]: https://img.shields.io/badge/AWS-20232A?style=for-the-badge&logo=amazon&logoColor=FFAC1C
[Openpdf]: https://img.shields.io/badge/OpenPDF-F3F2ED?style=for-the-badge&logo=adobe&logoColor=DE3163
[MySql]: https://img.shields.io/badge/MySql-F28C28?style=for-the-badge&logo=mysql&logoColor=white
[Next.js]: https://img.shields.io/badge/next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white
[Next-url]: https://nextjs.org/
[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB
[React-url]: https://reactjs.org/
[Vue.js]: https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D
[Vue-url]: https://vuejs.org/
[Okta]: https://img.shields.io/badge/OKTA-00008b?style=for-the-badge&logo=okta&logoColor=white
[Angular.io]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white
[Angular-url]: https://angular.io/
[Svelte.dev]: https://img.shields.io/badge/Svelte-4A4A55?style=for-the-badge&logo=svelte&logoColor=FF3E00
[Svelte-url]: https://svelte.dev/
[Laravel.com]: https://img.shields.io/badge/Laravel-FF2D20?style=for-the-badge&logo=laravel&logoColor=white
[Laravel-url]: https://laravel.com
[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white
[Bootstrap-url]: https://getbootstrap.com
[JQuery.com]: https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white
[JQuery-url]: https://jquery.com 
