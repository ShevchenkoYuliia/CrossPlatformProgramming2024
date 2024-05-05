package beans;

import java.beans.*;

public class DataSheetGraphBeanInfo extends SimpleBeanInfo {
    private PropertyDescriptor[] propertyDescriptors;
    public DataSheetGraphBeanInfo() {
        try {
            propertyDescriptors = new PropertyDescriptor[]{
                    new PropertyDescriptor("color",DataSheetGraph.class),
                    new PropertyDescriptor("filled",DataSheetGraph.class),
                    new PropertyDescriptor("deltaX",DataSheetGraph.class),
                    new PropertyDescriptor("deltaY",DataSheetGraph.class)
            };
        } catch (IntrospectionException e) {}
    }
    public PropertyDescriptor[] getPropertyDescriptors() {
        return propertyDescriptors;
    }
}