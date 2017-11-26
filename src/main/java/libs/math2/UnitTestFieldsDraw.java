package libs.math2;

import java.awt.Graphics;
import java.lang.reflect.Method;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import libs.UnitTest;

public class UnitTestFieldsDraw {

    private final UnitTest test;

    public UnitTestFieldsDraw(UnitTest test) {
        this.test = test;
    }
    
    public void drawAllShapesFromGetters(Graphics g)
    {
        ArrayList<Method> methods = getAllShapeGetters();
        
        methods.forEach((method) -> 
        {
            
        });
    }

    private ArrayList<Method> getAllShapeGetters() {
        Class c = test.getClass();
        ArrayList<Method> getters = new ArrayList<>();
        for (Method method : c.getDeclaredMethods()) {
            if (method.getAnnotation(PostConstruct.class) != null) {
                if (method.getName().startsWith("get")) {
                    String methodReturnTypeName = method.getGenericReturnType().getTypeName();
                    if (methodReturnTypeName.equals(CircleAG.class.getSimpleName())
                            || methodReturnTypeName.equals(LineAG.class.getSimpleName())
                            || methodReturnTypeName.equals(LineSection.class.getSimpleName())
                            || methodReturnTypeName.equals(PointAG.class.getSimpleName())
                            || methodReturnTypeName.equals(PolygonAG.class.getSimpleName())) {
                        getters.add(method);
                    }
                }
            }
        }
        return getters;
    }

}
