package mensajeria.controlador;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import mensajeria.modelo.Empresa;

/**
 *
 * @author Álvaro
 */
public class EmpresaControlador {
    private final File file;
    private FileOutputStream fileOutput;
    private ObjectOutputStream dataOutput;
    private FileInputStream fileInput;
    private ObjectInputStream dataInput;
    private List<Empresa> listaEmpresas;

    public EmpresaControlador() throws FileNotFoundException, IOException, ClassNotFoundException {
        file = new File("Empresas.dat");
        if (!file.exists()) {
            crearFicheroPrimeraVez(file);
        }
        leerEmpresa();
    }
    
    public void leerEmpresa() throws FileNotFoundException, IOException, ClassNotFoundException {
        listaEmpresas = new ArrayList<>();
        fileInput = new FileInputStream(file);
        dataInput = new ObjectInputStream(fileInput);
        try {
            while (true) {
                Empresa empresa = (Empresa) dataInput.readObject();
                listaEmpresas.add(empresa);
            }
        } catch (EOFException eofe) {
            System.out.println("fin de lectura");
        }
        dataInput.close();
        fileInput.close();
    }
    
    private void crearFicheroPrimeraVez(File file) throws FileNotFoundException, IOException {
        fileOutput = new FileOutputStream(file);
        dataOutput = new ObjectOutputStream(fileOutput);
        dataOutput.writeObject(new Empresa(0, null, null, null, null, false));
        dataOutput.close();
        fileOutput.close();
    }
    
    public void escribirEmpresa(List<Empresa> lista) throws FileNotFoundException, IOException {
        fileOutput = new FileOutputStream(file);
        dataOutput = new ObjectOutputStream(fileOutput);
        Iterator<Empresa> it = lista.iterator();
        while (it.hasNext()) {
            dataOutput.writeObject(it.next());
        }
        dataOutput.close();
        fileOutput.close();
    }

    public List<Empresa> getListaEmpresas() {
        return listaEmpresas;
    }
    
    
}
