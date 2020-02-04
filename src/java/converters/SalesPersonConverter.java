//package converters;
//
//import database.EmployeeHandler;
//import database.SalesPerson;
//import java.sql.Time;
//import javax.faces.application.FacesMessage;
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.Converter;
//import javax.faces.convert.ConverterException;
//import javax.faces.convert.FacesConverter;
//
//@FacesConverter("salesPersonConverter")
//public class SalesPersonConverter implements Converter
//{
//
//    private static final transient EmployeeHandler employeeHandler = new EmployeeHandler();
//
//    @Override
//    public Object getAsObject(FacesContext context,
//            UIComponent component,
//            String newValue) throws ConverterException
//    {
//        if (newValue.isEmpty())
//        {
//            return newValue;
//        }
//
//        String[] parts = newValue.split(":");
//
//        try
//        {
//            int empId = Integer.parseInt(parts[0]);
//            return employeeHandler.findEmployeeById(empId);
//        }
//        catch (NumberFormatException nfe)
//        {
//            String msgDetail = "Error converting the selected person";
//            FacesMessage msg = new FacesMessage("SalesPerson conversion error",
//                    msgDetail);
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//            throw new ConverterException(msg);
//        }
//    }
//
//    @Override
//    public String getAsString(FacesContext context,
//            UIComponent component,
//            Object value) throws ConverterException
//    {
//        if (value == null)
//        {
//            return "";
//        }
//
//        if (value instanceof SalesPerson)
//        {
//            return value.toString();
//        }
//
//        String msgDetail = "Unexpected type " + value.getClass().getName();
//        FacesMessage msg = new FacesMessage("SalesPerson conversion error",
//                msgDetail);
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//        throw new ConverterException(msg);
//    }
//}
