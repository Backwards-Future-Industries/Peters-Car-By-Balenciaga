package utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;


public class SPILocater {

    ArrayList<Object> arrayList = new ArrayList();

    public <T> List<T> localteAll(Class service){

        ServiceLoader serviceLoader = ServiceLoader.load(service);
        for (T instance : serviceLoader){
            arrayList.add(c);
        }

        return arrayList;
    }
}
