package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		System.out.println("Entre com os dados do aluguel. ");
		System.out.print("Modelo do carro: ");
		String carModel = sc.nextLine();
		System.out.print("Retirada (dd/MM/yyyy hh:mm) ");
		LocalDateTime startDate = LocalDateTime.parse(sc.nextLine(), dtf);
		System.out.print("Devolução (dd/MM/yyyy hh:mm) ");
		LocalDateTime finishDate = LocalDateTime.parse(sc.nextLine(), dtf);
		
		CarRental cr = new CarRental(startDate,finishDate,new Vehicle(carModel));
		
		System.out.print("Preço por hora: ");
		double pricePerHour = sc.nextDouble();
		
		System.out.print("Preço por dia: ");
		double pricePerDay = sc.nextDouble();
		
		RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
		rentalService.processInvoice(cr);
		
		System.out.println("Dados da Fatura: ");
		System.out.print("Pagamento base: " + String.format("%.2f", cr.getInvoice().getBasicPayment()));
		System.out.print("Pagamento imposto: " + String.format("%.2f", cr.getInvoice().getTax()));
		System.out.print("Pagamento TOTAL: " + String.format("%.2f", cr.getInvoice().getTotalPayment()));
				
		sc.close();
	}

}
