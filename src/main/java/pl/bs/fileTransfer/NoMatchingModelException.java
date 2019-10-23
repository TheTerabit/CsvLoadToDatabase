package pl.bs.fileTransfer;

public class NoMatchingModelException extends Exception {

    @Override
    public String getMessage(){
        return "There is no model maching name of the file";
    }

}
