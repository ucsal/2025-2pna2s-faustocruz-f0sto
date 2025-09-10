package br.com.mariojp.solid.srp;

public class ReceiptService {
	
	public String generate(Order order) {
		double subtotal = generateSubTotal(order);
		double tax = generateTax(subtotal, 0.10); //Taxa 10 fixa :(
		double total = subtotal + tax;
		
		return ReceiptFormatter(order, subtotal, tax, total).toString();
	}
	
	public double generateSubTotal(Order order) {
		return order.getItems().stream().mapToDouble(i -> i.getUnitPrice() * i.getQuantity()).sum();
	}
	
	public double generateTax(double subtotal, double tax) {
		return subtotal * tax;
	}
	
	public StringBuilder ReceiptFormatter(Order order, double subtotal, double tax, double total) {
		StringBuilder sb = new StringBuilder(); //Formatando o Recibo
		sb.append("=== RECIBO ===\n");
		for (var i : order.getItems()) {
			sb.append(i.getName()).append(" x").append(i.getQuantity()).append(" = ").append(i.getUnitPrice() * i.getQuantity())
					.append("\n");
		}
		sb.append("Subtotal: ").append(subtotal).append("\n");
		sb.append("Tax: ").append(tax).append("\n");
		sb.append("Total: ").append(total).append("\n");
		return sb;
	}
	
}
