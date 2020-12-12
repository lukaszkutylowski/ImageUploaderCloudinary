package lukaszkutylowski.frontend.service;

import lukaszkutylowski.model.Image;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrontendService {

    public String indexHtml() {
        return """

        <html>
        <head>
        </head>
        <body>
            <h4>Image Uploader Application</h4>
            <div>
                <a href='/upload_image'>
                    <button style="width:200px">Upload [Admin]</button>
                </a>
            </div>
            <div>
            <a href='/gallery'>
                <button style="width:200px">Gallery [User]</button>
            </a>
            </div>
            <div>
                <a href='/logout'>
                    <button style="width:200px">Logout</button>
                </a>
            </div>
        </body>
        </html>

        """;
    }

    public String uploadHtml() {
        return """

        <html>
        <head>
        </head>
        <body>
            <h4>Image Uploader Application</h4>
            <form action="http://localhost:8080/upload" method="post">
                <input name="path" type="text" style="width: 600px;"/>
                <button type="submit">Upload</button>
            </form>
        </body>
        </html>

        """;
    }

    public String galleryHtml(List<Image> list) {
        StringBuilder gallery = new StringBuilder();

        gallery.append("""

        <html>
        <head>
        </head>
        <body>
        
        """);

        for(Image i : list) {
            gallery.append("<img src='" + i.getImageAddress() + "'/>");
        }

        gallery.append("""
        
        <div>
            <button>
                <a href="http://localhost:8080/" style="text-decoration: none; color: black;">Main Page</a>
            </button>
        </div>
        
        </body>
        </html>

        """);

        return gallery.toString();
    }

    public String uploadDoneHtml() {
        return """

        <html>
        <head>
        </head>
        <body>

        <h4>Image Upload done.</h4>
        <button>
            <a href="http://localhost:8080/" style="text-decoration: none; color: black;">Main Page</a>
        </button>
        
        </body>
        </html>

        """;
    }
}
