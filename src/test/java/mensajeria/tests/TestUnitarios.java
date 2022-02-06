package mensajeria.tests;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mensajeria.controlador.EmpresaControlador;
import mensajeria.modelo.Empresa;
import mensajeria.vista.Ventana;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Álvaro
 */
public class TestUnitarios {

    public TestUnitarios() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void aniadirEmpresaListasEmpresas_EnVista_test(){
        Ventana instance = new Ventana();
        
        int sizeBefore_1 = instance.listaEmpresas.size();
        int sizeBefore_2 = instance.listaEmpresasMostradas.size();
        
        int id_empresa = instance.listaEmpresas.get(instance.listaEmpresas.size() - 1).getId_empresa() + 1;
        Empresa e = new Empresa(id_empresa, "", "", "", "", false);
        instance.aniadirEmpresa(e);
        
        int expResult_1 = sizeBefore_1 + 1;
        int expResult_2 = sizeBefore_2 + 1;
        
        int result_1 = instance.listaEmpresas.size();
        int result_2 = instance.listaEmpresasMostradas.size();
        
        assertEquals(expResult_1, result_1);
        assertEquals(expResult_2, result_2);
    }
    
    @Test
    public void leerFicheroEmpresas_TrasEscribir_TrasAniadirEmpresa_EnControlador_test(){
        try {
            //Lee el fichero y carga la lista. Se almacena el tamaño.
            EmpresaControlador instance = new EmpresaControlador();
            List<Empresa> lista= instance.getListaEmpresas();
            int sizeBefore = lista.size();
            
            //Se añade un elemento a la lista y se escribe en el fichero
            int id_empresa = instance.getListaEmpresas().get(instance.getListaEmpresas().size() - 1).getId_empresa() + 1;
            lista.add(new Empresa(id_empresa, "", "", "", "", false));
            instance.escribirEmpresa(lista);
            
            //Se vuelve a leer el fichero y se almacena el nuevo tamaño
            lista = instance.getListaEmpresas();
            int sizeAfter = lista.size();
            
            //La segunda lectura debe tener un elemento más que la primera lectura
            int expResult = sizeBefore + 1;
            int result = sizeAfter;
            assertEquals(expResult, result);
            
        } catch (IOException ex) {
            Logger.getLogger(TestUnitarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TestUnitarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void eliminarEmpresa_borradoLogico_SetActivoEnFalse_EnVista_test(){
        Ventana instance = new Ventana();
        
        int id_empresa = instance.listaEmpresas.get(instance.listaEmpresas.size() - 1).getId_empresa() + 1;
        Empresa e = new Empresa(id_empresa, "", "", "", "", true);
        instance.aniadirEmpresa(e);
        
        instance.eliminarEmpresa(e.getId_empresa());
        
        boolean expResult = false;
        boolean result = e.isActivo();
        
        assertEquals(expResult, result);
    }
    
    @Test
    public void modificarNombreEmpresa_test(){
        Ventana instance = new Ventana();
        
        int elementoModificar = 1;
        Empresa e1 = instance.listaEmpresasMostradas.get(elementoModificar);
        String nombreAntesCambiar = e1.getNombre_empresa();
        String nombreNuevo = "jlsdhf";
        
        instance.modificarEmpresa(elementoModificar, nombreNuevo, e1.getCif(), e1.getDirector(), e1.getWeb());
        
        Empresa e2 = instance.listaEmpresasMostradas.get(elementoModificar);
        String nombreCambiado = e2.getNombre_empresa();
        
        //Con esta linea vuelve el elemento al estado anterior al test
        instance.modificarEmpresa(elementoModificar, nombreAntesCambiar, e1.getCif(), e1.getDirector(), e1.getWeb());
        
        assertEquals(nombreNuevo, nombreCambiado);
    }
    
}
