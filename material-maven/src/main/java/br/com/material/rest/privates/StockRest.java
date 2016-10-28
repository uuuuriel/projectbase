package br.com.material.rest.privates;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.com.material.object.Stock;
import br.com.material.rest.abs.RestAbstract;
import br.com.material.service.StockService;

@Path("stock")
public class StockRest extends RestAbstract<Stock,Integer,StockService>{

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Override
	public Response add(String json) {
		// TODO Auto-generated method stub
		return super.add(json);
	}
	
}
