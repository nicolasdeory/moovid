package aiss.api.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
import aiss.model.repository.Metadata;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.BoundExtractedResult;

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
	public MP3 getMP3(@PathParam("id") String id){
		
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
	public Response uploadMP3(@Context UriInfo uriInfo,Metadata metadata) {  //Podria ponerse void como return
		if(metadata==null) {		//se pueden comprobar otros parametros obligatorios
			throw new BadRequestException("El metadata no puede ser nulo");
		}
		MP3 mp3 = new MP3(metadata);
		repository.addMP3(mp3);
		/*
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(),"get");
		URI uri = ub.build(mp3.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(mp3);
		return resp.build();*/
		Response resp1 = Response.status(204).header("ETag", mp3.getId()).build();
		return resp1;
	}
	
	@POST
	@Path("/searchmp3")
	@Produces("application/json")
	@Consumes("application/json")
	public Response searchMP3(@Context UriInfo uriInfo, FiltroMP3 Fmp3) {
		if(Fmp3==null || Fmp3.getName()==null || Fmp3.getName().equals("")) {		//se pueden comprobar otros parametros obligatorios
			throw new BadRequestException("El filtro no puede ser nulo");
		}
		List<BoundExtractedResult<MP3>> results = FuzzySearch.extractSorted(Fmp3.getName(), repository.listMP3(), x->x.getMetadata().toString(), new FuzzyMetadataScore());
		List<MP3> mp3 = new ArrayList<MP3>();
		mp3 = results.stream().filter(x->x.getScore()>70).sorted(Comparator.comparing(x->x.getScore()))
		.sorted(Comparator.reverseOrder()).map(x->x.getReferent()).collect(Collectors.toList());
		
		/*UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(),"get");
		URI uri = ub.build(mp3.hashCode());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(mp3);*/
		Response resp1 = Response.ok().entity(mp3).build();
		return resp1;
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
}
