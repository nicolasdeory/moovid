package aiss.api.resources;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import aiss.model.repository.MP3;
import aiss.model.repository.MP3Repository;

@Path("/song")
public class SongResource {
	
	public static SongResource _instance=null;
	MP3Repository repository;
	
	private SongResource() {
		repository = MP3Repository.getInstance();
	}
	
	public static SongResource getInstance()
	{
		if(_instance==null)
			_instance=new SongResource();
		return _instance; 
	}
	
	@GET
	@Path("/getmp3/{id}")
	@Produces("application/json")
	public MP3 get(@PathParam("id") String id){
		
		MP3 mp3 = repository.getMP3(id);
		
		if(mp3 == null) {
			throw new NotFoundException("No se ha encontrado el MP3 de id: " + id);
		}
		
		return mp3;
	
	}
	
	@PUT
	@Path("/uploadmp3")
	@Consumes("application/json")
	public Response uploadMP3(MP3 mp3)) {  //Podria ponerse void como return
		MP3 oldmp3 = repository.getMP3(mp3.getId());
		if(oldmp3 == null) {
			throw new NotFoundException("No se ha encontrado el MP3 de id: " + mp3.getId());
		}
		
		//El siguiente if hay que repetirlo en cada atributo del mp3
		if(mp3.get|Atributo|==null) {
			oldmp3.set|Atributo|(mp3.get|Atributo|());
		}
		
		return Response.noContent().build();
	}
	
	@POST
	@Path("/searchmp3")
	@Produces("application/json")
	@Consumes("application/json")
	public Response searchMP3(@Context UriInfo uriInfo, MP3 mp3) {
		if(mp3==null) {		//se pueden comprobar otros parametros obligatorios
			throw new BadRequestException("El mp3 no puede ser nulo");
		}
		repository.addMP3(mp3);
		
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(),"get");
		URI uri = ub.build(mp3.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(mp3);
		return resp.build();
	}
	
	@DELETE
	@Path("/deletemp3/{id}")
	public Response deleteMP3(@PathParam("id") String id) {
		MP3 tobedeleted = repository.getMP3(id);
		if(tobedeleted == null) {
			throw new NotFoundException("No se ha encontrado el MP3 a borrar de id: " + id);
		}else repository.removeMP3(id);
		
		return Response.noContent().build();
	}
	
	// PUT - subir un mp3 /song/uploadmp3
	// GET - mp3 song id /song/getmp3
	// POST - search mp3 song /song/searchmp3
	// DELETE - borrar mp3 con id /song/deletemp3

}
