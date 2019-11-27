package com.example.curso.entidades.enuns;

public enum StatusPedido {

	ESPERANDO_PAGAMENTO(1),
	PAGO(2),
	EMPACOTANDO(3),
	ENTREGUE(4),
	CANCELADO(5);
	
	private Integer code;
	
	private StatusPedido(Integer code) {
		this.code = code;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public static StatusPedido valorDe(Integer code) {
		for (StatusPedido pedido : StatusPedido.values()) {
			if (pedido.getCode() == code) {
				return pedido;
			}
		}
		throw new IllegalStateException("Código invalido");
	}
}
