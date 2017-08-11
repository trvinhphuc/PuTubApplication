package webservice.controller;

import java.awt.Image;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.StreamingOutput;

import com.sun.jersey.core.util.ReaderWriter;

import webservice.bo.VideoBO;

import webservice.pojo.VideoVO;
import webservice.utils.JsonUtil;

@Path("/video2")
public class PlayVideoService {
	
	/*creator: Truong Vinh Phuc 
	 *function: get video information
	 *date: 11/08/2017
	 *note:
	 */
	@GET
	@Path("/{param}")
	// @Produces(MediaType.)
	public Response getVid(@PathParam("param") String id) {
		JsonUtil json = new JsonUtil();
		VideoBO getVid = new VideoBO();
		VideoVO vid = new VideoVO();
		try {
			vid = getVid.getVideoInfo(Integer.parseInt(id));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String output = json.buildJson(vid);
		System.out.println(vid.getVideoname());
		// System.out.println(output);
		return Response.status(200).entity(output).build();

	}
	
	/*creator: Truong Vinh Phuc 
	 *function: get video from database and stream it to front-end
	 *date: 11/08/2017
	 *note:the video viewed from front-end cannot skip forward!
	 *need to improve the streaming function
	 */
	@GET
	@Path("/test/{param}")
	@Produces("video/mp4")
	public Response getVid_2(@PathParam("param") String id) {
		JsonUtil json = new JsonUtil();
		VideoBO getVid = new VideoBO();
		VideoVO vid = new VideoVO();
		try {
			vid = getVid.getVideo(Integer.parseInt(id));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputStream test = new ByteArrayInputStream(vid.getVideo());
		// String output = json.buildJson(vid);
		//System.out.println(vid.getVideoname());
		// System.out.println(output);
		return Response.ok(test, MediaType.APPLICATION_OCTET_STREAM).build();
	}
	
	/*creator: Truong Vinh Phuc 
	 *function: testing streaming video to front-end
	 *date: 11/08/2017
	 *note:the video viewed from front-end cannot skip forward!
	 *need to improve the streaming function
	 */
	@GET
	@Path("/test2/{param}")
    @Produces("video/mp4")
	public Response getVid_3(@PathParam("param") String id) {
        return Response.ok(new FeedReturnStreamingOutput()).build();
    }

    public static class FeedReturnStreamingOutput implements StreamingOutput {

        @Override
        public void write(OutputStream output)
                throws IOException, WebApplicationException {
            try {
            	VideoBO getVid = new VideoBO();
				VideoVO vid = new VideoVO();
            	vid = getVid.getVideo(Integer.parseInt("8"));
				InputStream input = new ByteArrayInputStream(vid.getVideo());
				int read = 0;
				byte[] bytes = new byte[1024];

				while ((read = input.read(bytes)) != -1) {
					output.write(bytes, 0, read);
				}
                output.flush();
            } catch (InterruptedException e) {  throw new RuntimeException(e); } catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    
    /*creator: Truong Vinh Phuc 
	 *function: for user to download the video
	 *date: 11/08/2017
	 *note:
	 */
    @GET
	@Path("/test3/{param}")
    @Produces("video/mp4")
    public Response testen(@PathParam("param") final String id) throws Exception {
        StreamingOutput entity = new StreamingOutput() {
            @Override
            public void write(OutputStream output) throws IOException, 
            WebApplicationException {
            	VideoBO getVid = new VideoBO();
				VideoVO vid = new VideoVO();
            	try {
					vid = getVid.getVideo(Integer.parseInt(id));
					//System.out.println("test3");
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
				InputStream input = new ByteArrayInputStream(vid.getVideo());
                ReaderWriter.writeTo(input, output);
                output.flush();
                output.close();
            }
        };
        return Response.ok(entity).build();
    }
    /*
    @GET
    @Path("/test4/{param}")
    @Produces("video/mp4")
    public Response streamAudio(@HeaderParam("Range") String range) throws Exception {
    	VideoBO getVid = new VideoBO();
		VideoVO vid = new VideoVO();
		try {
			vid = getVid.getVideo(Integer.parseInt(id));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputStream test = new ByteArrayInputStream(vid.getVideo());
        return buildStream(test, range);
    }

    private Response buildStream(final File asset, final String range) throws Exception {
        // range not requested : Firefox, Opera, IE do not send range headers
        if (range == null) {
            StreamingOutput streamer = new StreamingOutput() {
                @Override
                public void write(final OutputStream output) throws IOException, WebApplicationException {

                    final FileChannel inputChannel = new FileInputStream(asset).getChannel();
                    final WritableByteChannel outputChannel = Channels.newChannel(output);
                    try {
                        inputChannel.transferTo(0, inputChannel.size(), outputChannel);
                    } finally {
                        // closing the channels
                        inputChannel.close();
                        outputChannel.close();
                    }
                }
            };
            return Response.ok(streamer).header(HttpHeaders.CONTENT_LENGTH, asset.length()).build();
        }

        String[] ranges = range.split("=")[1].split("-");
        final int from = Integer.parseInt(ranges[0]);
        int chunk_size = 2048;
		
        int to = chunk_size + from;
        if (to >= asset.length()) {
            to = (int) (asset.length() - 1);
        }
        if (ranges.length == 2) {
            to = Integer.parseInt(ranges[1]);
        }

        final String responseRange = String.format("bytes %d-%d/%d", from, to, asset.length());
        final RandomAccessFile raf = new RandomAccessFile(asset, "r");
        raf.seek(from);

        final int len = to - from + 1;
        final MediaStreamer streamer = new MediaStreamer(len, raf);
        Response.ResponseBuilder res = Response.status(Status.OK).entity(streamer)
                .header("Accept-Ranges", "bytes")
                .header("Content-Range", responseRange)
                .header(HttpHeaders.CONTENT_LENGTH, streamer.getLenth());
        return res.build();
    }
    public class MediaStreamer implements StreamingOutput {

        private int length;
        private RandomAccessFile raf;
        final byte[] buf = new byte[4096];

        public MediaStreamer(int length, RandomAccessFile raf) {
            this.length = length;
            this.raf = raf;
        }

        @Override
        public void write(OutputStream outputStream) throws IOException, WebApplicationException {
            try {
                while( length != 0) {
                    int read = raf.read(buf, 0, buf.length > length ? length : buf.length);
                    outputStream.write(buf, 0, read);
                    length -= read;
                }
            } finally {
                raf.close();
            }
        }

        public int getLenth() {
            return length;
        }
    }
	/*
	@GET
	@Path("/test2/{param}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getVid_3(@PathParam("param") final String id) {
		StreamingOutput stream = new StreamingOutput() {
			@Override
			public void write(OutputStream output) throws IOException, WebApplicationException {
				JsonUtil json = new JsonUtil();
				VideoBO getVid = new VideoBO();
				VideoVO vid = new VideoVO();
				try {
					vid = getVid.getVideo(Integer.parseInt(id));
					InputStream input = new ByteArrayInputStream(vid.getVideo());
					int read = 0;
					byte[] bytes = new byte[1024];

					while ((read = input.read(bytes)) != -1) {
						output.write(bytes, 0, read);
					}
	                output.flush();
	                //output.close();
	            }catch (Exception e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		} 
				
			}
		};
		return Response.ok(stream).build();
	}
*/
}
