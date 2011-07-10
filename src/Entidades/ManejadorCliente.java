package Entidades;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ManejadorCliente {

    private EntityManagerFactory emf;
    private EntityManager em;

    public ManejadorCliente() {
        emf = Persistence.createEntityManagerFactory("JManagePU");
        em = emf.createEntityManager();
    }

    public int agregarCliente(String nombre, String apellido, long limiteCredito) {
        Cliente temp = new Cliente();
        temp.setNombre(nombre);
        temp.setApellido(apellido);
        temp.setLimiteCredito(limiteCredito);
        try {
            em.getTransaction().begin();
            em.persist(temp);
            em.getTransaction().commit();
            return temp.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean modificarCliente(Cliente temp, String nombre, String apellido, long limiteCredito) {
        return modificarCliente(temp.getId(), nombre, apellido, limiteCredito);
    }

    public boolean modificarCliente(int id, String nombre, String apellido, long limiteCredito) {
        //Buscar Para Confirmar
        Cliente temp1 = buscarClienteById(id);
        if (temp1 != null) {
            temp1.setNombre(nombre);
            temp1.setApellido(apellido);
            temp1.setLimiteCredito(limiteCredito);
            try {
                em.getTransaction().begin();
                em.merge(temp1);
                em.getTransaction().commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public boolean borrarClienteById(int id) {
        Cliente temp1 = buscarClienteById(id);
        if (temp1 != null) {
            try {
                System.out.println(temp1.getNombre());
                em.getTransaction().begin();
                em.remove(temp1);
                em.getTransaction().commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public Cliente buscarClienteById(int id) {
        return em.find(Cliente.class, id);
    }

    public List<Cliente> buscarClienteByNombre(String nombre) {
        return em.createQuery("SELECT c FROM Cliente c WHERE LOWER(c.nombre) LIKE :buscandoNombre").setParameter("buscandoNombre", nombre).getResultList();
    }
    
    public List<Cliente> listaClientes(){
        return em.createQuery("SELECT c FROM Cliente c").getResultList();
    }
}
/*public boolean modificarCliente(int id, String nombre, String apellido, long limiteCredito) {
//Buscar Para Confirmar
if (buscarClienteById(id).size() != -1) {
for (Cliente temp1 : buscarClienteById(id)) {
temp1.setNombre(nombre);
temp1.setApellido(apellido);
temp1.setLimiteCredito(limiteCredito);
try {
em.getTransaction().begin();
em.refresh(temp1);
em.getTransaction().commit();
return true;
} catch (Exception e) {
e.printStackTrace();
return false;
}
}

}
return false;
}*/

    /*public List<Cliente> buscarClienteById(int id) {
        return em.createQuery("SELECT c FROM Cliente c WHERE c.id LIKE :buscandoId").setParameter("buscandoId", id).getResultList();
    }*/