import java.awt.*;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteClient extends Remote {
    /*public void drawLine(int x, int y, int startX, int startY, int thickness, Color color) throws RemoteException;
    public void drawSquare(int x, int y, int startX, int startY, int thickness, Color color) throws  RemoteException;
    public void drawBrush(int x, int y, int startX, int startY, int thickness, Color color) throws  RemoteException;
    public void drawPoint(int x, int y, int thickness, Color color) throws  RemoteException; */
    public void drawIt(int x, int y, int startX, int startY, int thickness, Color color, int id) throws  RemoteException;
    public void allClientsDrawIt(int x, int y, int startX, int startY, int thickness, Color color, int id) throws  RemoteException;
}
