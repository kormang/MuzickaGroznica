package net.etfbl.muzickagroznica.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import net.etfbl.muzickagroznica.model.entities.MusicContent;
import net.etfbl.muzickagroznica.model.entities.User;
import net.etfbl.muzickagroznica.service.ContentService;

/**
 * Servlet implementation class AudioFileUploadServlet
 */
public class AudioFileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private ContentService contentService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AudioFileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(ServletConfig sc) throws ServletException{
    	WebApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(sc.getServletContext());
    	contentService = (ContentService) appContext.getBean("contentService");
	
    }
    
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
				
		if(!isMultipart){
			response.sendRedirect(request.getContextPath() + "/super/audio_upload_error");
			return;
		}
		
		if(contentService == null){
	    	WebApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
			contentService = (ContentService) appContext.getBean("contentService");
		}
		
		ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
		try {
			List<FileItem> items = sfu.parseRequest(request);
			
			String artist = null;
			String genre = null;
			String name = null;
			String lyrics = null;
			byte[] bytes = null;
			String originFileName = null;
			
			for(FileItem i : items){
				
				if(i.isFormField()){
					String fieldName = i.getFieldName();
					String fieldValue = i.getString();
					switch (fieldName){
					
					case "artist":
						artist = new String(fieldValue.getBytes("ISO-8859-1"), "UTF-8");
						break;
					case "genre":
						genre = new String(fieldValue.getBytes("ISO-8859-1"), "UTF-8");
						break;
					case "name":
						name = new String(fieldValue.getBytes("ISO-8859-1"), "UTF-8");
						break;
					case "lyrics":
						lyrics = new String(fieldValue.getBytes("ISO-8859-1"), "UTF-8");
						break;
					default:
						System.err.println(fieldName + "=" + fieldValue);
						
					}
					
				} else {
					bytes = i.get();
					originFileName = i.getName();
				}
				
			}
			
			if(bytes == null || name == null || artist == null || genre == null || lyrics == null){
				throw new RuntimeException("Not all fields are set");
			}
			
			int publisherId =  ((User)request.getSession().getAttribute("user")).getId();
			
			MusicContent mc = contentService.addNewContent(name, artist, genre, lyrics, bytes, originFileName, publisherId);
			
			response.sendRedirect(request.getContextPath() + "/content/listen/"+mc.getId());
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/super/audio_upload_error");
		} catch (Exception ex){
			ex.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/super/audio_upload_error");
		}
		
		
		
	}

}
