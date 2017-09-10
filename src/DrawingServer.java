
import sun.security.x509.IPAddressName;

import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DrawingServer implements RemoteDrawingServer {
    HashMap<String,RemoteClient> clients = new HashMap<String,RemoteClient>();

    public DrawingServer() throws RemoteException {};

    public static void main(String[] args) {
        try {
            DrawingServer server = new DrawingServer();
            RemoteDrawingServer stub = (RemoteDrawingServer) UnicastRemoteObject.exportObject(server, 12344);

            String address = InetAddress.getLocalHost().getHostAddress();
            System.out.println(address);
            System.setProperty("java.rmi.server.hostname", address);
            Registry registry = LocateRegistry.createRegistry(12345);
            registry.rebind("Drawing Server", stub);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void allClientsDrawIt(int x, int y, int startX, int startY, int thickness, Color color, int id) throws RemoteException {
        for (HashMap.Entry<String,RemoteClient> client: clients.entrySet()) {
            client.getValue().drawIt(x, y, startX, startY, thickness, color, id);
        }
    }

    @Override
    public void register() throws RemoteException {
        try {
            Registry registryClient = LocateRegistry.getRegistry(RemoteServer.getClientHost(), 12347);
            RemoteClient clientStub = (RemoteClient) registryClient.lookup("Client");
            clients.put(RemoteServer.getClientHost(),clientStub);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
