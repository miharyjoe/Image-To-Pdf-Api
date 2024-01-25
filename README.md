# Image To Pdf Api

An API to convert images to pdf in spring boot

## How to use the API

### The endpoint URL :  "{{url}}/convert"

- Open Postman and create a new request.
- Set the HTTP method to POST.

- Set the request URL to the endpoint that you have defined in your Spring Boot application.

- In the request headers, add a new key-value pair with the key Content-Type and the value multipart/form-data.

- In the request body, select the form-data option.
Add a new key-value pair with the key images, and set the value to the image file that you want to upload.

- Click the "Send" button to send the request.