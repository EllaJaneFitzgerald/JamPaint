
import java.awt.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class DrawingClient implements RemoteClient {
    RemoteDrawingServer server;

    public DrawingClient() throws RemoteException {};

    public void create() {
        try {
            String address = GetActiveIPv4Addr.getIP();
            System.setProperty("java.rmi.server.hostname", address);

            Registry registry = LocateRegistry.getRegistry("192.168.0.146", 12345);
            server = (RemoteDrawingServer) registry.lookup("Drawing Server");

            RemoteClient stub = (RemoteClient) UnicastRemoteObject.exportObject(this, 12346);
            Registry registryClient = LocateRegistry.createRegistry(12347);
            registryClient.rebind("Client", stub);

            server.register();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void allClientsDrawIt(int x, int y, int startX, int startY, int thickness, Color color, int id) throws RemoteException {
        server.allClientsDrawIt(x, y, startX, startY, thickness, color, id);
    }

    public void drawIt(int x, int y, int startX, int startY, int thickness, Color color, int id) {
        switch (id) {
            case Dot.ID: Dot.drawIt(x,y,startX,startY,thickness,color); break;
            case Brush.ID: Brush.drawIt(x,y,startX,startY,thickness,color); break;
            case Line.ID: Line.drawIt(x,y,startX,startY,thickness,color); break;
            case Square.ID: Square.drawIt(x,y,startX,startY,thickness,color); break;
        }
    }
}
