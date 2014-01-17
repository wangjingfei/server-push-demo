package me.anying.cometd;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.UnavailableException;

import me.anying.stock.StockPriceEmitter;

public class Initializer extends GenericServlet {
	private static final long serialVersionUID = -4108191503660015299L;

	private StockPriceEmitter emitter;

	@Override
	public void init() throws ServletException {
		emitter = new StockPriceEmitter();

		// This service is created by @Service annotation.
		StockPriceService service = (StockPriceService) getServletContext()
				.getAttribute(StockPriceService.class.getName());

		emitter.addListener(service);

		emitter.start();
	}

	@Override
	public void destroy() {
		emitter.stop();
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		throw new UnavailableException("Initializer");
	}
}
