import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Server {
	

    public void server(int port)  throws Exception {
        //Create ServerSocketChannel，monitor port.
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(port));
        // set non-block mode
        ssc.configureBlocking(false);
        // Register the selector for ssc.
        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        // Create processor
        while (true) {
            // Waiting for the request, each time waiting for blocking for 2s, after more than 2s, the thread continues to run downwards. If you pass in 0 or do not pass the parameter, it will block.
            if (selector.select(2000) == 0) {
                continue;
            }
            // Get the pending SelectionKey.
            Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();

            while (keyIter.hasNext()) {
                SelectionKey key = keyIter.next();
                // Start a new thread to process the SelectionKey.
                new Thread(new HttpHandler(key)).run();
                // After processing, remove the currently used key from the pending SelectionKey iterator.
                keyIter.remove();
            }
        }
    }

    private static class HttpHandler implements Runnable {
        private int bufferSize = 1024;
        private String localCharset = "UTF-8";
        private SelectionKey key;

        public HttpHandler(SelectionKey key) {
            this.key = key;
        }

        public void handleAccept() throws IOException {
            SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
            clientChannel.configureBlocking(false);
            clientChannel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));
        }

        public void handleRead() throws IOException {
            // get channel.
            SocketChannel sc = (SocketChannel) key.channel();
            // get buffer and reset.
            ByteBuffer buffer = (ByteBuffer) key.attachment();
            buffer.clear();
            // lose if no content is read.
            if (sc.read(buffer) == -1) {
                sc.close();
            } else {
                // Receiving request data.
                buffer.flip();
                String receivedString = Charset.forName(localCharset).newDecoder().decode(buffer).toString();
                System.out.println("Receive data");
                System.out.println(receivedString);
                //System.out.println("Receive finish"); Test code.
                String[] strarray=receivedString.split("\n");
                
                byte[] sourceByte = strarray[8].getBytes();
                
                if(null != strarray[8]){
                	try {
                		File file = new File("./tmp/phonebook.json");	//File path format (path + file name)
                		if (!file.exists()) {	//Create a file if the file for storage does not exist, first create a directory
                			File dir = new File(file.getParent());
                			dir.mkdirs();
                			file.createNewFile();
                		}
                		FileOutputStream outStream = new FileOutputStream(file);	//File output stream is used to write data to a file.
                		outStream.write(sourceByte);
                		outStream.close();	//Close the file output stream.
                	} catch (Exception e) {
                		e.printStackTrace();

                }
                
                
                // Console print request header
                String[] requestMessage = receivedString.split("\r\n");
                for (String s : requestMessage) {
                    System.out.println(s);
                    // Encountered a blank line indicating that the message header has been printed.
                    if (s.isEmpty()) {
                        break;
                    }
                }

                String path = "./tmp/phonebook.json";
                File file = new File(path);
                InputStream in =new FileInputStream(file);
                String resource = new Scanner(in).useDelimiter("\\Z").next();


                // The console prints the first line of information
                String[] firstLine = requestMessage[0].split(" ");
                System.out.println();
                System.out.println("Method:\t" + firstLine[0]);
                System.out.println("url:\t" + firstLine[1]);
                System.out.println("HTTP Version:\t" + firstLine[2]);
                System.out.println();

                // Return to Client.
                StringBuilder sendString = new StringBuilder();
                sendString.append("HTTP/1.1 200 OK\r\n");//In response to the first line of the message, 200 indicates that the processing was successful.
                sendString.append("Content-Type:text/html;charset=" + localCharset + "\r\n");
                sendString.append("\r\n");// Add a blank line after the end of the message header

                
             
                sendString.append("<!DOCTYPE html> \n");
                sendString.append("<html lang=\"en\"> \n");
                sendString.append("<head> \n");
                sendString.append("    <meta charset=\"UTF-8\"> \n");
                sendString.append("    <title>Title</title> \n");
                sendString.append("</head> \n ");
                sendString.append("<body> \n");
                sendString.append(resource+"\n");
                sendString.append("</body> \n");
                sendString.append("</html> \n");

                buffer = ByteBuffer.wrap(sendString.toString().getBytes(localCharset));
                sc.write(buffer);
                sc.close();
            }}
        }

        @Override
        public void run() {
            try {
                // When receiving a connection request
                if (key.isAcceptable()) {
                    handleAccept();
                }
                // Read data
                if (key.isReadable()) {
                    handleRead();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}
