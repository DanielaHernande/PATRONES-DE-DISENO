package PatronesDeComportamiento.Strategy;

public interface ReportStrategy {

    void generateReport(List<Task> tasks);
}

public class PDFReportStrategy implements ReportStrategy {
    
    @Override
    public void generateReport(List<Task> tasks) {

        System.out.println("Generating PDF report");
        // Lógica para generar reporte en PDF
    }
}

public class ExcelReportStrategy implements ReportStrategy {
    
    @Override
    public void generateReport(List<Task> tasks) 
    {
        System.out.println("Generating Excel report");
        // Lógica para generar reporte en Excel
    }
}

// Uso del patrón Strategy
public class ReportGenerator {
    
    private ReportStrategy strategy;

    public void setStrategy(ReportStrategy strategy) {
        this.strategy = strategy;
    }

    public void generateReport(List<Task> tasks) {
        
        strategy.generateReport(tasks);
    }
}
