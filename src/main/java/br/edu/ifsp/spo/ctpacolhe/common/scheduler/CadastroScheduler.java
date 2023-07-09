package br.edu.ifsp.spo.ctpacolhe.common.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.edu.ifsp.spo.ctpacolhe.service.CadastroService;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CadastroScheduler {
	@Autowired
	private CadastroService cadastroService;
	
	@Scheduled(fixedRateString = "${scheduler.unverified-account.interval}")
    public void removeCadastrosNaoVerificados() {
		cadastroService.removeCadastrosNaoVerificados();
    }
}
