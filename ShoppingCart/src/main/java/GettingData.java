import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("serial")
@WebServlet("/GettingData")

public class GettingData extends HttpServlet {
    private static final String JSON_FILE_PATH = "products.json";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String realPath = getServletContext().getRealPath(JSON_FILE_PATH);
            if (realPath == null) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Invalid file path");
                return;
            }
            try (FileInputStream fis = new FileInputStream(realPath);
                 InputStreamReader isr = new InputStreamReader(fis);
                 BufferedReader reader = new BufferedReader(isr)) {
                 StringBuilder jsonContent = new StringBuilder();
                 String line;
               while ((line = reader.readLine()) != null) {
                   jsonContent.append(line);
               }
               ObjectMapper objectMapper = new ObjectMapper();
               Object jsonData = objectMapper.readValue(jsonContent.toString(), Object.class);
               String jsonResponse = objectMapper.writeValueAsString(jsonData);
               response.setContentType("application/json");
               response.getWriter().write(jsonResponse);
            } catch (IOException e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
