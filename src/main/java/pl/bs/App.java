package pl.bs;

import pl.bs.fileTransfer.FileTransferFacade;


public class App 
{
    public static void main( String[] args )
    {
        final String path=System.getProperty("user.dir") + "\\src\\main\\java\\pl\\bs\\resources\\configuration.json";
        final FileTransferFacade fileTransferFacade = new FileTransferFacade();
        fileTransferFacade.start(path);
    }
}
