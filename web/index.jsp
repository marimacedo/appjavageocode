<%-- 
    Document   : index
    Created on : 03/05/2018, 21:08:00
    Author     : maric
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <title>File Uploading Form</title>
   </head>
   
   <body>
      <h3>File Upload:</h3>
      Select a file to upload: <br />
      <form action="EnderecoServlet" method = "post" enctype = "multipart/form-data">
         <input type="file" name="file" />
         <br />
         <input type = "submit" value = "Ler arquivo" />
      </form>
   </body>
</html>
