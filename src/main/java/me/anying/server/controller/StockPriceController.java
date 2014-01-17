package me.anying.server.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.wink.server.internal.providers.entity.html.HtmlDescriptor;

@Path("/stocks")
public class StockPriceController {
	@GET
	@Produces(MediaType.TEXT_HTML)
	public HtmlDescriptor getMessageBox() {
		return new HtmlDescriptor((Object) null, "/stock/stock.jsp");
	}
}
