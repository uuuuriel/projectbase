package br.com.material.rest.privates;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.com.material.object.Category;
import br.com.material.rest.abs.RestAbstract;
import br.com.material.service.CategoryService;

@Path("/category")
public class CategoryRest extends RestAbstract<Category, Integer, CategoryService> {

	@GET
	@Path("/{id}")
	@Produces("application/json")
	@Override
	public Response getObject(@PathParam("id") Integer id) {
		// TODO Auto-generated method stub
		return super.getObject(id);
	}

	@GET
	@Produces("application/json")
	@Override
	public Response list() {
		// TODO Auto-generated method stub
		return super.list();
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Override
	public Response add(String json) {
		// TODO Auto-generated method stub
		return super.add(json);
		//return this.util.getResponsePrivate();
	}

	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	@Override
	public Response edit(String json) {
		// TODO Auto-generated method stub
		return super.edit(json);
	}

	@DELETE
	@Path("/{id}")
	@Produces("application/json")
	@Override
	public Response remove(@PathParam("id") Integer id) {
		// TODO Auto-generated method stub
		return super.remove(id);
	}
}
