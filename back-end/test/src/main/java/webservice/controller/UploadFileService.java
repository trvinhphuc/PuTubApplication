package webservice.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/file")
public class UploadFileService {
	
	/*creator: Truong Vinh Phuc 
	 *function: catch the video or the avatar source from front-end then save it into file  
	 *date: 11/08/2017
	 *note:
	 */
	public String Location = null;
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {
		String type = null;
		if (fileDetail.getFileName().endsWith("jpg"))
			type = "Avatar";
		if (fileDetail.getFileName().endsWith("mp4"))
			type = "Video";
		String uploadedFileLocation = "C://uploaded/" + type + "/" + fileDetail.getFileName();

		// save it
		writeToFile(uploadedInputStream, uploadedFileLocation);
		
		String output = "File uploaded successfully. " ;
		// uploadedInputStream.close();
		return Response.status(200).entity(output).build();

	}

	
	// save uploaded file to new location
	private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {

		try {
			OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
			// System.out.println("Closed");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	

}