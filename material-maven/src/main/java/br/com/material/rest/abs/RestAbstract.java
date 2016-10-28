package br.com.material.rest.abs;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.ws.rs.core.Response;

import br.com.material.service.ServiceBase;
import br.com.material.util.UtilRest;
public class RestAbstract<E, ID, SERVICE extends ServiceBase<E, ID>> {
	private Class<E> entity;
	private Class<SERVICE> service;
	protected UtilRest util = new UtilRest();

	@SuppressWarnings("unchecked")
	public RestAbstract() {

		@SuppressWarnings("rawtypes")
		Class<? extends RestAbstract> realClass = getClass();

		ParameterizedType superclass = (ParameterizedType) realClass
				.getGenericSuperclass();

		this.entity = (Class<E>) superclass.getActualTypeArguments()[0];
		this.service = (Class<SERVICE>) superclass.getActualTypeArguments()[2];
	}
	public Response getObject(ID id) {

		try {
			SERVICE service = this.service.newInstance();

			E e = service.getObject(id);

			return this.util.getResponseList(e);
		} catch (Exception e) {
			return this.util.getResponseError(e);
		}
	}

	public Response list() {

		try {
			SERVICE service = this.service.newInstance();

			List<E> e = service.list();

			return this.util.getResponseList(e);
		} catch (Exception e) {
			return this.util.getResponseError(e);
		}
	}

	public Response add(String json) {

		try {
			E e = this.util.getObjectMapper().readValue(json, this.entity);

			SERVICE service = this.service.newInstance();

			service.add(e);

			return this.util.getResponseAdd(e);
		} catch (Exception e) {
			return this.util.getResponseError(e);
		}
	}

	public Response edit(String json) {

		try {
			E e = this.util.getObjectMapper().readValue(json, this.entity);

			SERVICE service = this.service.newInstance();

			service.edit(e);

			return this.util.getResponseEdit();
		} catch (Exception e) {
			return this.util.getResponseError(e);
		}
	}

	public Response remove(ID id) {

		try {
			SERVICE service = this.service.newInstance();

			service.remove(id);

			return this.util.getResponseRemove();
		} catch (Exception e) {
			return this.util.getResponseError(e);
		}
	}
}
