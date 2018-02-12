package in.clouthink.synergy.shared.util;

public class ErrorUtils {
    
    public static String toString(Exception e) {
        StringBuffer result = new StringBuffer();
        for (StackTraceElement element : e.getStackTrace()) {
            result.append(element.toString()).append("\n");
        }
        return result.toString();
    }
}
