package aiss.api.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

import aiss.model.repository.FiltroMP3;
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
	@Produces("application/json")
	@Consumes("application/json")
	public Response uploadMP3(@Context UriInfo uriInfo,MP3 mp3) {  //Podria ponerse void como return
		if(mp3==null) {		//se pueden comprobar otros parametros obligatorios
			throw new BadRequestException("El mp3 no puede ser nulo");
		}
		if(mp3.getId()==null) mp3.setId(UUID.randomUUID().toString());
		repository.addMP3(mp3);
		
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(),"get");
		URI uri = ub.build(mp3.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(mp3);
		return resp.build();
	}
	
	@POST
	@Path("/searchmp3")
	@Produces("application/json")
	@Consumes("application/json")
	public Response searchMP3(@Context UriInfo uriInfo, FiltroMP3 Fmp3) {
		if(Fmp3==null) {		//se pueden comprobar otros parametros obligatorios
			throw new BadRequestException("El mp3 no puede ser nulo");
		}
		List<MP3> mp3 = new ArrayList<MP3>();
		for(MP3 mp3Posible: repository.listMP3()) {
			if(Fmp3.getName()!=null && Fmp3.getSize()!=null) {
				if(Fmp3.getName().equals(mp3Posible.getName()) && Fmp3.getSize().getType().equals("greater") 
						&& Fmp3.getSize().getValue()<mp3Posible.getSize()) mp3.add(mp3Posible);
				if(Fmp3.getName().equals(mp3Posible.getName()) && Fmp3.getSize().getType().equals("lesser") 
						&& Fmp3.getSize().getValue()>mp3Posible.getSize()) mp3.add(mp3Posible);
			}
			if(Fmp3.getName()!=null && Fmp3.getName().equals(mp3Posible.getName())) mp3.add(mp3Posible);
			if(Fmp3.getSize()!=null && Fmp3.getSize().getType().equals("greater") 
					&& Fmp3.getSize().getValue()<mp3Posible.getSize()) mp3.add(mp3Posible);
			if(Fmp3.getSize()!=null && Fmp3.getSize().getType().equals("lesser") 
					&& Fmp3.getSize().getValue()>mp3Posible.getSize()) mp3.add(mp3Posible);
		}
		
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(),"get");
		URI uri = ub.build(mp3.hashCode());
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
