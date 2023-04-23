package utilities;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;


public class SPILocater {

    /*
    All places where the SPIlocator is needed, create a new method like down below

    public Collection<IPlugin> getPlugin(){
        return SPILocater.localteAll(IPlugin.class);
    }

    Remeber to make it a collection that contains the Interface you want to call to access it's methods
     */


    public static  <T> List<T> localteAll(Class service){
        ServiceLoader<T> serviceLoader = ServiceLoader.load(service);

        List<T> list = new ArrayList<T>();

        for (T instance : serviceLoader){
            list.add(instance);
        }

        return list;
    }
}
