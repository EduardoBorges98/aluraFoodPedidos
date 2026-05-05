package br.com.alurafood.pedidos.amqp;

import br.com.alurafood.pedidos.dto.PagamentoDto;
import br.com.alurafood.pedidos.dto.StatusDto;
import br.com.alurafood.pedidos.model.Status;
import br.com.alurafood.pedidos.service.PedidoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PagamentoListener {

    private final PedidoService pedidoService;

    public PagamentoListener(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @RabbitListener(queues = "pagamento.concluido")
    public void recebeMensagem(PagamentoDto pagamento) {
        System.out.println("Recebi pagamento: " + pagamento.getId());
        System.out.println("Pedido ID: " + pagamento.getPedidoId());
        System.out.println("Status do pagamento: " + pagamento.getStatus());

        if (!"CONFIRMADO".equals(String.valueOf(pagamento.getStatus()))) {
            System.out.println("Pagamento ainda não confirmado. Ignorando mensagem.");
            return;
        }

        StatusDto statusDto = new StatusDto();
        statusDto.setStatus(Status.PAGO);

        pedidoService.atualizaStatus(pagamento.getPedidoId(), statusDto);
    }
}