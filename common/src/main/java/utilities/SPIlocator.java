package utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;


public class SPIlocator {

    /*
    All places where the SPIlocator is needed, create a new method like down below

    public Collection<IPlugin> getPlugin(){
        return SPIlocator.locateAll(IPlugin.class);
    }

    Remeber to make it a collection that contains the Interface you want to call to access it's methods

    Below a for each loop can be seen, remember to make for each iterate over the datatype wich methods you want to use

    for (IPlugin iPlugin : getPlugin()){
            iPlugin.create();
        }
     */


    public static  <T> List<T> locateAll(Class service){
        ServiceLoader<T> serviceLoader = ServiceLoader.load(service);

        List<T> list = new ArrayList<T>();

        for (T instance : serviceLoader){
            list.add(instance);
        }

        return list;
    }
}
