/**
 * @author Jesus Armando Macias Benitez
 */
package mx.com.amx.wsd.yog.backoffice.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import mx.com.amx.wsd.yog.backoffice.dao.exception.DAOException;
import mx.com.amx.wsd.yog.backoffice.model.Nota;
import mx.com.amx.wsd.yog.backoffice.model.NotaMagazine;

/**
 * @author  Jesus Armando Macias Benitez
 *
 */
public class BackOfficeDAO {
	
	private static Logger LOG = Logger.getLogger(BackOfficeDAO.class);
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;	
	
	
	/**
	 * Metodo que borra todas las noticias de un magazine
	 * @param idMagazine
	 * @return int, 0=false, 1=true
	 * @throws DAOException
	 * */
	public int deleteAllNoticias(String idMagazine)  throws DAOException{
		LOG.debug("=======================================================");	
		LOG.debug(" ----  deleteAllNoticias  [ MagazineDAO ] --- ");
		LOG.debug(" ---   idMagazine: "+idMagazine+" --- ");	
		LOG.debug("=======================================================");	
		try {
			StringBuffer sql=new StringBuffer();
			sql.append(" DELETE FROM yog_ba_i_nota_magazine ");
			sql.append(" WHERE FC_ID_MAGAZINE = ? ");
			return jdbcTemplate.update(sql.toString(),new Object[]{idMagazine});					
		} catch (Exception e) {
			LOG.error("Exception deleteAll [ MagazineDAO ] : ",e);
			throw new DAOException(e.getMessage());			
		}
	}
	
	
	

	/**
	 * Metodo que inserta notas de un magazine
	 * @param notaMagazineDTO
	 * @return int
	 * @throws DAOException
	 * */
	public int insertNotaMagazine(NotaMagazine notaMagazine) throws DAOException{
		LOG.debug("=======================================================");	
		LOG.debug(" --- insertNotaMagazine  [ MagazineDAO ] --- ");
		LOG.debug("idContenido: "+notaMagazine.getIdContenido());
		LOG.debug("idMagazine: "+notaMagazine.getIdMagazine());
		LOG.debug("orden: "+notaMagazine.getOrden());
		LOG.debug("UrlExterna: "+notaMagazine.getUrlExterna());
		LOG.debug("=======================================================");	
		
		
		
		try {
			StringBuffer sql=new StringBuffer();
			sql.append(" INSERT INTO yog_ba_i_nota_magazine ( FC_ID_CONTENIDO, FC_ID_MAGAZINE, FI_ORDEN, FC_URL_EXTERNA )");
			sql.append(" values ( ?, ?, ?, ?) ");
			 Object []datos= new Object[] { 
					 notaMagazine.getIdContenido(),
					 notaMagazine.getIdMagazine(),
					 notaMagazine.getOrden(),
					 notaMagazine.getUrlExterna()
						};
			 LOG.debug(" *** SQL: "+sql.toString());
			return jdbcTemplate.update(sql.toString(),datos);
		} catch (Exception e) {			
			LOG.error(" --- Exception insertNotaMagazine [ MagazineDAO ] : ",e);
			throw new DAOException();
		}
	}
	
	
	
	public List<Nota> getNotasByCategoria( String idCategoria , Integer pagina , Integer resultadosPorPagina ) throws DAOException{
		LOG.debug("=======================================================");	
		LOG.debug(" ---  getNotasByCategoria en [ MagazineDAO ] ---");
		LOG.debug("idCategoria: "+idCategoria);
		LOG.debug("pagina: "+pagina);
		LOG.debug("resultadosPorPagina: "+resultadosPorPagina);
		LOG.debug("=======================================================");	
		
		try {
			
			Integer paginaInicial = ( pagina * resultadosPorPagina ) - ( resultadosPorPagina -1 );
			Integer paginaFinal	  = ( pagina * resultadosPorPagina );  

			StringBuffer query = new StringBuffer();
			query.append(" SELECT * FROM (   ");
			query.append(" 		SELECT @rownum:=@rownum + 1 AS ROWNUMBER , X.*  ");
			query.append(" 		FROM (  SELECT  CONCAT(COALESCE(categoria.FC_FRIENDLY_URL,''),'/detalle/', COALESCE(nota.FC_FRIENDLY_URL,'')) as fcLinkDetalle,    ");
			query.append(" 						nota.FC_ID_TIPO_NOTA as fcIdTipoNota,   ");
			query.append(" 						categoria.FC_DESCRIPCION as fcDescripcionCategoria,  ");
			query.append(" 						nota.FC_ID_CONTENIDO as fcIdContenido,   ");
			query.append(" 						nota.FC_TITULO as fcTitulo,   ");
			query.append(" 						REPLACE( REPLACE(nota.FC_DESCRIPCION, Char(13), ''), Char(10),'')as fcDescripcion,   ");
			query.append(" 						nota.FC_ID_CATEGORIA as fcIdCategoria,   ");
			query.append(" 						nota.FC_FRIENDLY_URL as fcNombre,   ");
			query.append(" 						nota.FC_IMAGEN_PRINCIPAL as fcImgPrincipal,   ");
			query.append(" 						DATE_FORMAT( nota.FD_FECHA_PUBLICACION , '%d-%m-%Y') as fcFechaPublicacion   ");
			query.append(" 			    FROM yog_ba_n_nota nota,  yog_ba_c_categoria categoria,   ");
			query.append(" 					 yog_ba_c_deporte deporte,  yog_ba_c_tipo_nota tipo_nota, ");
			query.append("                   yog_ba_c_tipo_video tipo_video  ");
			query.append(" 				WHERE nota.FC_ID_CATEGORIA = ?   ");
			query.append(" 				AND nota.FC_ID_CATEGORIA=categoria.FC_ID_CATEGORIA  ");
			query.append(" 				AND nota.FC_ID_DEPORTE= deporte.FC_ID_DEPORTE ");
			query.append(" 				AND nota.FC_ID_TIPO_NOTA= tipo_nota.FC_ID_TIPO_NOTA  ");
			query.append("            	AND nota.FC_ID_TIPO_VIDEO= tipo_video.FC_ID_TIPO_VIDEO ");
			query.append(" 		        ORDER BY nota.FD_FECHA_PUBLICACION desc  )  ");
			query.append(" 		X JOIN (SELECT @rownum := 0) R) as TT   ");
			query.append("      WHERE ROWNUMBER BETWEEN ? AND ?");			
			LOG.debug("query: "+query.toString());
			return  jdbcTemplate.query ( query.toString() ,new Object [] { idCategoria, paginaInicial , paginaFinal } , new BeanPropertyRowMapper<Nota>( Nota.class) );			
		} catch (Exception e) {
			LOG.error("Exception getNotasByCategoria [ MagazineDAO ]  ",e );
			throw new DAOException(e.getMessage()); 
		}

	}
	
	
	
	public Nota getNotaByFcNombre(String fcNombre) throws DAOException{		
		LOG.debug("=======================================================");	
		LOG.debug("---  getNotasByFcNombre [ MagazineDAO ] --- ");
		LOG.debug("fcNombre: "+fcNombre);
		LOG.debug("=======================================================");	
		
		
		
		try {
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" select ");   
			sbQuery.append(" N.FC_ID_TIPO_NOTA as fcIdTipoNota,  ");
			sbQuery.append(" C.FC_DESCRIPCION as fcDescripcionCategoria,  ");
			sbQuery.append(" N.FC_ID_CONTENIDO as fcIdContenido,  ");
			sbQuery.append(" N.FC_TITULO as fcTitulo,  ");
			sbQuery.append(" REPLACE( REPLACE(N.FC_DESCRIPCION, Char(13), ''), Char(10),'')as fcDescripcion,  ");
			sbQuery.append(" N.FC_ID_CATEGORIA as fcIdCategoria,  ");
			sbQuery.append(" CASE ");
			sbQuery.append(" WHEN TS.FC_ID_TIPO_SECCION = 'especiales' ");
			sbQuery.append(" THEN CONCAT('/',COALESCE(TS.FC_ID_TIPO_SECCION,''),'/',COALESCE(S.FC_FRIENDLY_URL,''),'/',COALESCE(C.FC_FRIENDLY_URL,''),'/detalle/', COALESCE(N.FC_NOMBRE,'')) "); 
			sbQuery.append(" ELSE ");
			sbQuery.append(" CONCAT('/',COALESCE(TS.FC_ID_TIPO_SECCION,''),'s/',COALESCE(S.FC_FRIENDLY_URL,''),'/',COALESCE(C.FC_FRIENDLY_URL,''),'/detalle/', COALESCE(N.FC_NOMBRE,'')) ");
			sbQuery.append(" END AS fcLinkDetalle, ");  
			sbQuery.append(" N.FC_NOMBRE as fcNombre, ");
			sbQuery.append(" N.FC_IMAGEN_PRINCIPAL as fcImgPrincipal,  ");
			sbQuery.append(" DATE_FORMAT( N.FD_FECHA_PUBLICACION , '%d-%m-%Y') as fcFechaPublicacion ");
			sbQuery.append(" from UNO_MX_H_NOTA as N, ");
			sbQuery.append(" UNO_MX_C_CATEGORIA C,  ");
			sbQuery.append(" UNO_MX_C_SECCION S,  ");
			sbQuery.append(" UNO_MX_C_TIPO_SECCION TS  ");
			sbQuery.append(" where  ");
			sbQuery.append(" C.FC_ID_CATEGORIA=N.FC_ID_CATEGORIA  ");
			sbQuery.append(" AND C.FC_ID_SECCION=S.FC_ID_SECCION AND S.FC_ID_TIPO_SECCION=TS.FC_ID_TIPO_SECCION   ");
			sbQuery.append(" AND N.FC_NOMBRE  =  ?   ");
			
			sbQuery.append(" order by N.FD_FECHA_PUBLICACION desc ");
			sbQuery.append(" limit 100 ");			
			LOG.debug("sbQuery: "+sbQuery.toString());
		
			List< Nota > list = jdbcTemplate.query ( sbQuery.toString() ,new Object[]{ fcNombre} , new BeanPropertyRowMapper<Nota>( Nota.class) );
			
			LOG.debug("=======================================================");	
			LOG.debug("list Size : "+ list.size());
			LOG.debug("=======================================================");	
			
			if(list!=null && list.size()>0)
				return list.get(0);
			
			
			
		} catch (Exception e) {			
			LOG.error(" Exception getNotasByFcNombre [ MagazineDAO ] ",e );
			throw new DAOException(e.getMessage());
		}		
		
		return null;
	}
	
	
	public List<Nota> getNotasByTag( String idTag , Integer pagina , Integer resultadosPorPagina ) throws DAOException{
		LOG.debug("=======================================================");	
		LOG.debug(" --- getNotasByCategoria en [ MagazineDAO ] --- ");
		LOG.debug("idCategoria: "+idTag);
		LOG.debug("pagina: "+pagina);
		LOG.debug("resultadosPorPagina: "+resultadosPorPagina);
		LOG.debug("=======================================================");	
		try {			
			Integer paginaInicial = ( pagina * resultadosPorPagina ) - ( resultadosPorPagina -1 );
			Integer paginaFinal	  = ( pagina * resultadosPorPagina );  
			StringBuffer query = new StringBuffer();
			query.append(" SELECT * FROM (  ");
			query.append(" SELECT @rownum:=@rownum + 1 AS ROWNUMBER , X.* FROM ( ");
			query.append(" SELECT nota.FC_ID_CATEGORIA fcIdCategoria ,  ");
			query.append("     	categoria.FC_DESCRIPCION fcDescripcionCategoria, ");
			query.append(" 		nota.FC_ID_CONTENIDO fcIdContenido , ");
			query.append(" 		nota.FC_DESCRIPCION fcDescripcion , ");
			query.append(" 		nota.FC_NOMBRE fcNombre , ");
			query.append(" 		nota.FC_ID_TIPO_NOTA fcIdTipoNota,  ");
			query.append(" 		nota.FD_FECHA_PUBLICACION fcFechaPublicacion,  ");
			query.append(" 		nota.FC_IMAGEN_PRINCIPAL fcImgPrincipal,  ");
			query.append(" 		nota.FI_BAN_PATROCINIO fiBanPatrocinio,  ");
			query.append(" 		nota.FC_TITULO fcTitulo,  ");
			query.append("	CONCAT (tipoSeccion.FC_FRIENDLY_URL,'/',seccion.FC_FRIENDLY_URL,'/',categoria.FC_FRIENDLY_URL , '/detalle/',nota.FC_NOMBRE ,'/') fcLinkDetalle  ");
			query.append(" FROM uno_mx_n_nota nota");
			query.append(" LEFT JOIN uno_mx_c_categoria categoria ON nota.fc_id_categoria = categoria.fc_id_categoria ");
			query.append(" LEFT JOIN uno_mx_c_seccion seccion ON categoria.FC_ID_SECCION = seccion.FC_ID_SECCION ");
			query.append(" LEFT JOIN uno_mx_c_tipo_seccion tipoSeccion ON seccion.fc_id_tipo_seccion = tipoSeccion.fc_id_tipo_seccion ");
			query.append(" LEFT JOIN uno_app_i_tag_nota tag ON nota.FC_ID_CONTENIDO = tag.FC_ID_CONTENIDO ");			
			query.append(" WHERE tag.FC_ID_TAG = ? ");
			query.append(" ORDER BY nota.FD_FECHA_PUBLICACION DESC ");
			query.append(" )  X JOIN (SELECT @rownum := 0) R) as TT ");
			query.append(" WHERE ROWNUMBER BETWEEN ? AND ?");				
			LOG.debug("query: "+query.toString());			
			return  jdbcTemplate.query ( query.toString() ,
																	new Object [] { idTag, paginaInicial , paginaFinal } , 
																	new BeanPropertyRowMapper<Nota>( Nota.class) );			
		} catch (Exception e) {
			LOG.error("Exception getNotasByCategoria [ MagazineDAO ] ",e );
			throw new DAOException(e.getMessage()); 
		}

	}
	
	
	
	
	/**
	 * Metodo que obtiene el detalle de una nota
	 * @param idContenido
	 * @param idMagazine
	 * @return NotaDTO
	 * @throws DAOException
	 * */
	public Nota getNotaMagazine(String idContenido, String idMagazine) throws DAOException{
		LOG.debug("=======================================================");	
		LOG.debug(" --- getNotaMagazine en [ MagazineDAO ] ---");
		LOG.debug("idContenido: "+idContenido);
		LOG.debug("idMagazine: "+idMagazine);
		LOG.debug("=======================================================");	
		try {
		
			StringBuffer sbQuery = new StringBuffer();				
			sbQuery.append(" select ");  
			sbQuery.append(" 'noticias' as fcTabla, ");
			sbQuery.append(" N.FC_ID_TIPO_NOTA as fcIdTipoNota, ");
			sbQuery.append(" C.FC_DESCRIPCION as fcDescripcionCategoria, ");
			sbQuery.append(" N.FC_ID_CONTENIDO as fcIdContenido, ");
			sbQuery.append(" N.FC_TITULO as fcTitulo, ");
			sbQuery.append(" REPLACE( REPLACE(N.FC_DESCRIPCION, Char(13), ''), Char(10),'')as fcDescripcion, ");
			sbQuery.append(" N.FC_ID_CATEGORIA as fcIdCategoria, ");
			sbQuery.append(" CONCAT('http://www.unotv.com/',COALESCE(TS.FC_ID_TIPO_SECCION,''),'s/',COALESCE(S.FC_FRIENDLY_URL,''),'/',COALESCE(C.FC_FRIENDLY_URL,''),'/detalle/', COALESCE(N.FC_NOMBRE,'')) as fcLinkDetalle,  ");
			sbQuery.append(" CASE ");
			sbQuery.append(" WHEN INM.FC_URL_EXTERNA is null or INM.FC_URL_EXTERNA = '' THEN N.FC_NOMBRE ");
			sbQuery.append(" ELSE COALESCE(INM.FC_URL_EXTERNA, '') ");
			sbQuery.append(" END As fcNombre,");
			sbQuery.append(" N.FC_IMAGEN_PRINCIPAL as fcImgPrincipal, ");
			sbQuery.append(" INM.FI_BAN_PATROCINIO as fiBanPatrocinio, ");
			sbQuery.append(" DATE_FORMAT( N.FD_FECHA_PUBLICACION , '%d-%m-%Y') as fcFechaPublicacion ");
			sbQuery.append(" from UNO_MX_N_NOTA as N, ");
			sbQuery.append(" UNO_MX_I_NOTA_MAGAZINE INM, ");
			sbQuery.append(" UNO_MX_C_CATEGORIA C, ");
			sbQuery.append(" UNO_MX_C_SECCION S, ");
			sbQuery.append(" UNO_MX_C_TIPO_SECCION TS ");
			sbQuery.append(" where INM.FC_ID_CONTENIDO=N.FC_ID_CONTENIDO ");
			sbQuery.append(" AND C.FC_ID_CATEGORIA=N.FC_ID_CATEGORIA ");
			sbQuery.append(" AND C.FC_ID_SECCION=S.FC_ID_SECCION AND S.FC_ID_TIPO_SECCION=TS.FC_ID_TIPO_SECCION "); 
			sbQuery.append(" AND INM.FC_ID_CONTENIDO= ? ");
			sbQuery.append(" AND INM.FC_ID_MAGAZINE= ? ");
			sbQuery.append(" order by N.FD_FECHA_PUBLICACION desc ");
			LOG.debug("sbQuery: "+sbQuery.toString());
			List< Nota > list=jdbcTemplate.query ( sbQuery.toString() ,
					new Object [] { idContenido, idMagazine} , 
					new BeanPropertyRowMapper<Nota>( Nota.class) );

			return list.get(0);
		} catch (Exception e) {			
			LOG.error(" Exception getNotaMagazine [ MagazineDAO ] ",e );
			throw new DAOException(e.getMessage());
		}		
	}
	
	/**
	 * @param idMagazine
	 * @return List<NotaDTO>
	 * @throws DAOException
	 * */
	public List<Nota> getAllIdsNotesPublished(String idMagazine) throws DAOException{
		LOG.debug("=======================================================");	
		LOG.debug(" ---  getAllIdsNotesPublished en [ MagazineDAO ] --- ");
		LOG.debug("idMagazine: "+idMagazine);		
		LOG.debug("=======================================================");	
		try {
			StringBuffer sql=new StringBuffer();
			sql.append(" SELECT N.FC_ID_CONTENIDO as fcIdContenido, 'noticias' as fcImgPrincipal, N.FI_ORDEN ");
			sql.append(" FROM yog_ba_i_nota_magazine N ");
			sql.append(" WHERE N.FC_ID_MAGAZINE= ? ");
			sql.append(" ORDER BY N.FI_ORDEN ASC ");
			LOG.debug("sql: "+sql.toString());
			Object[] args = {idMagazine};			
			return jdbcTemplate.query(sql.toString() , args, new BeanPropertyRowMapper<Nota>(Nota.class));			
		} catch (Exception e) {
			LOG.error("Exception en getAllIdsNotesPublished [ MagazineDAO ]: ",e);
			throw new DAOException();
		}
	}
	

}
