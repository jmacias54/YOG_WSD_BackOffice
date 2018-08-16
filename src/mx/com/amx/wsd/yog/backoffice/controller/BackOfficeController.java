/**
 * @author Jesus Armando Macias Benitez
 */
package mx.com.amx.wsd.yog.backoffice.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;

import mx.com.amx.wsd.yog.backoffice.controller.exception.ControllerException;
import mx.com.amx.wsd.yog.backoffice.dao.BackOfficeDAO;
import mx.com.amx.wsd.yog.backoffice.model.Nota;
import mx.com.amx.wsd.yog.backoffice.model.NotaMagazine;




/**
 * @author  Jesus Armando Macias Benitez
 *
 */



@Controller
@RequestMapping("backOfficeController")
public class BackOfficeController {
	
	private static Logger LOG = Logger.getLogger(BackOfficeController.class);
	
	
	@Autowired
	private BackOfficeDAO backOfficeDAO;
	
	
	/**
	 * Metodo 
	 * @param idMagazine
	 * @param HttpServletResponse
	 * @return int
	 * @throws RestClientException
	 * */
	@RequestMapping( value = "deleteAllNoticias" , method=RequestMethod.POST , headers="Accept=application/json" )
	@ResponseBody
	public int deleteAllNoticias (@RequestBody String idMagazine, HttpServletResponse  response ) throws ControllerException {
		LOG.info(" ---  deleteAllNoticias [ MagazineController ] --- ");
		LOG.info("idMagazine: "+idMagazine);
		try {						
			return backOfficeDAO.deleteAllNoticias(idMagazine);
			
		} catch (Exception e ){
			LOG.error("Exception deleteAllNoticias [ MagazineController ] ",e);
			throw new ControllerException(e.getMessage());
		}	 			
	}
	
	
	
	/**
	 * Metodo  
	 * @param NotaMagazineDTO
	 * @param HttpServletResponse
	 * @return int
	 * @throws RestClientException
	 * */
	@RequestMapping( value = "insertNotaMagazine" , method=RequestMethod.POST , headers="Accept=application/json" )
	@ResponseBody
	public int insertNotaMagazine(@RequestBody NotaMagazine notaMagazine, HttpServletResponse response) throws ControllerException {
		LOG.info(" ---  insertNotaMagazine [ MagazineController ] --- ");
		LOG.info(" --- notaMagazine: "+notaMagazine.toString());
		try {						
			return backOfficeDAO.insertNotaMagazine(notaMagazine);
		}catch (Exception e ){
			LOG.error("Exception insertNotaMagazine  [ MagazineController ] ",e);
			throw new ControllerException(e.getMessage());
		}	 			
	}
	
	
	
	/**
	 * Metodo  
	 * @param String fcIdTipoSeccion
	 * @param HttpServletResponse
	 * @return List<CategoriaDTO>
	 * @throws RestClientException
	 * */
	@RequestMapping( value = "getNotasByCategoria" , method=RequestMethod.POST , headers="Accept=application/json" )
	@ResponseBody
	public List<Nota> getNotasByCategoria(@RequestParam("idCategoria") String idCategoria, @RequestParam("pagina") int pagina, @RequestParam("resultadosPorPagina") int resultadosPorPagina, HttpServletResponse response) throws RestClientException {
		LOG.info("---  getNotasByCategoria [ MagazineController ] --- ");
		LOG.info("idCategoria: "+idCategoria);
		LOG.info("pagina: "+pagina);
		LOG.info("resultadosPorPagina: "+resultadosPorPagina);
		try {						
			return backOfficeDAO.getNotasByCategoria(idCategoria,pagina,resultadosPorPagina);
		}catch (Exception e ){
			LOG.error("Exception getNotasByCategoria [ MagazineController ] ",e);
			throw new RestClientException(e.getMessage());
		}	 			
	}
	
	
	
	@RequestMapping( value = "getNotasByFcNombre" , method=RequestMethod.POST , headers="Accept=application/json" )
	@ResponseBody
	public Nota getNotasByFcNombre(@RequestBody String fcNombre ) throws RestClientException {
		LOG.info("---  getNotasByFcNombre [ MagazineController ] --- ");
		LOG.info("fcNombre: "+fcNombre);		
		try {						
			return backOfficeDAO.getNotaByFcNombre(fcNombre);
		} catch (Exception e ){
			LOG.error("Exception getNotasByFcNombre [ MagazineController ] ",e);
			throw new RestClientException(e.getMessage());
		}	 			
	}
	
	
	/**
	 * Metodo  
	 * @param String fcIdTipoSeccion
	 * @param HttpServletResponse
	 * @return List<CategoriaDTO>
	 * @throws RestClientException
	 * */
	@RequestMapping( value = "getNotasByTag" , method=RequestMethod.POST , headers="Accept=application/json" )
	@ResponseBody
	public List<Nota> getNotasByTag(@RequestParam("idTag") String idTag, @RequestParam("pagina") int pagina, 
									@RequestParam("resultadosPorPagina") int resultadosPorPagina, HttpServletResponse response) throws RestClientException {
		LOG.info("---  getNotasByTag [ MagazineController ] --- ");
		LOG.info("idCategoria: "+idTag);
		LOG.info("pagina: "+pagina);
		LOG.info("resultadosPorPagina: "+resultadosPorPagina);
		try {						
			return backOfficeDAO.getNotasByTag(idTag,pagina,resultadosPorPagina);
		}catch (Exception e ){
			LOG.error("Exception getNotasByTag  [ MagazineController ] ",e);
			throw new RestClientException(e.getMessage());
		}	 			
	}
	
	
	
	/**
	 * Metodo 
	 * @param idMagazine
	 * @param HttpServletResponse
	 * @return List<NotaDTO>
	 * @throws RestClientException
	 * */
	@RequestMapping( value = "getAllIdsNotesPublished" , method=RequestMethod.POST , headers="Accept=application/json" )
	@ResponseBody
	public List<Nota> getAllIdsNotesPublished (@RequestBody String idMagazine, HttpServletResponse  response ) throws RestClientException {
		LOG.info("---  getAllIdsNotesPublished [ MagazineController ] --- ");
		LOG.info("idMagazine: "+idMagazine);
		try {									
			 List<Nota> list = backOfficeDAO.getAllIdsNotesPublished(idMagazine);
			 LOG.debug("list:"+ list.size());
			 return list;
		} catch (Exception e ){
			LOG.error("Exception getAllIdsNotesPublished  [ MagazineController ] ",e);
			throw new RestClientException(e.getMessage());
		}	 			
	}
	
	
	
	/**
	 * Metodo 
	 * @RequestBody idContenido
	 * @RequestBody idMagazine
	 * @param HttpServletResponse
	 * @return NotaDTO
	 * @throws RestClientException
	 * */
	@RequestMapping( value = "getNotaMagazine" , method=RequestMethod.POST , headers="Accept=application/json" )
	@ResponseBody
	public Nota getNotaMagazine (@RequestParam("idContenido") String idContenido, @RequestParam("idMagazine") String idMagazine, HttpServletResponse  response ) throws RestClientException {
		LOG.info("---  getNotaMagazine [ MagazineController ] --- ");
		LOG.info("idContenido: "+idContenido);
		LOG.info("idMagazine: "+idMagazine);
		try {						
			return backOfficeDAO.getNotaMagazine(idContenido, idMagazine);
		} catch (Exception e ){
			LOG.error("Exception getNotaMagazine  [ MagazineController ] ",e);
			throw new RestClientException(e.getMessage());
		}	 			
	}

}
