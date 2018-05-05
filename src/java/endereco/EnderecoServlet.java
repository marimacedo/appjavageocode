/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endereco;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.apache.http.client.methods.HttpGet;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.SocketException;
import java.net.URLEncoder;
import javax.net.ssl.SSLException;

/**
 *
 * @author maric
 */
@WebServlet(name = "EnderecoServlet", urlPatterns = {"/EnderecoServlet"})
public class EnderecoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EnderecoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EnderecoServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 50 * 1024;
    private int maxMemSize = 4 * 1024;
    private File file;

    public void init() {
      // Get the file location where it would be stored.

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            //Le o arquivo

            List<String> listaEnderecos = new ArrayList<String>();
            listaEnderecos.add("rua mediterraneo florianopolis");
            listaEnderecos.add("rua pasteur curitiba");
            String endereco = request.getParameter("file-upload");
            ServletContext context = getServletContext();
            String enderecoCompleto = context.getRealPath(endereco);
            //BufferedReader buff = new BufferedReader(new FileReader(enderecoCompleto));
            //String str;
            //while((str = buff.readLine()) != null) {
            //if(str != null){
            //     listaEnderecos.add(str);
            //  }
            //}
            //buff.close();

           // String url = "http://maps.googleapis.com/maps/api/geocode/json?address=rua mediterraneo florianopolis&sensor=false&key=AIzaSyB9KgUyHRJwfggXDXteGomxkk5mECle0Lc";
            //AccessResponse access = getAccess(url, new AccessRequest());

            //String url = "http://maps.googleapis.com/maps/api/geocode/xml?latlng="+ params[0] + "," + params[1];

//            HttpClient httpclient = new DefaultHttpClient();
//
//            HttpGet request2 = new HttpGet(URLEncoder.encode(url));
//            InputStream in = httpclient.execute(request2).getEntity().getContent();
//
//            BufferedReader br = null;
//            StringBuilder sb = new StringBuilder();
//
//            br = new BufferedReader(new InputStreamReader(in));
//
//            String line = br.readLine();
//
//            String json = IOUtils.toString(in);
//
//            //Percorre a lista de endereços e consulta o geocode
//            String chaveMaps = "";
//            //HttpClient client = new HttpClient();
//            //String url = "https://maps.googleapis.com/maps/api/geocode/json?address={0}&sensor=false&key=" + chaveMaps;
//
//            List<Planilha> planilha = new ArrayList<Planilha>();
            
            GerarExcel(request, response);

        } catch (Exception e) {
              String a = e.getMessage();
        }
    }
    
    private void GerarExcel(HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException
    {
         File file = null;
        InputStream in = null;
        OutputStream outstream = null;
        try {
          response.reset();
          in = new FileInputStream(file);
          response.setContentType("application/vnd.ms-excel");
          response.addHeader("content-disposition", "attachment; filename=data.xls");
          outstream = response.getOutputStream();
          IOUtils.copyLarge(in, outstream);
         }
        catch (Exception e) {
//            out.write("Unable to download file");
        }finally {
            IOUtils.closeQuietly(outstream);
            IOUtils.closeQuietly(in);
  //          IOUtils.closeQuietly(out);
            if (file != null)
                file.delete();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public AccessResponse getAccess(String endPoint, AccessRequest accessRequest)
            throws IOException {
        // java 7 try-with-resources that closes the http client for us.
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // the http client can actually be re-used more than once
            HttpPost post = new HttpPost(endPoint);
            post.setHeader("Content-Type", "application/json");
            Gson gson = new GsonBuilder().create();
            post.setEntity(new StringEntity(gson.toJson(accessRequest), "UTF-8"));

            // It's much easier to use here a ResponseHandler because it closes the streams
            ResponseHandler<AccessResponse> responseHandler = new ResponseHandler<AccessResponse>() {
                @Override
                public AccessResponse handleResponse(final HttpResponse response) throws IOException {
                    StatusLine statusLine = response.getStatusLine();
                    HttpEntity entity = response.getEntity();

                    if (statusLine.getStatusCode() >= 300) {
                        throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
                    }

                    if (entity == null) {
                        throw new ClientProtocolException("Response contains no content");
                    }

                    Gson gson = new GsonBuilder().create();
          // The EntityUtils provides useful methods to read the response content.
                    // I also use the Gson lib to easily convert Json to Java objects and vise versa.
                    return gson.fromJson(EntityUtils.toString(entity), AccessResponse.class);
                }
            };

            return httpClient.execute(post, responseHandler);
        }
    }

}
