/**
 * @author Jesus Armando Macias Benitez
 */
package mx.com.amx.wsd.yog.backoffice.model;

import java.io.Serializable;

/**
 * @author Jesus Armando Macias Benitez
 *
 */
public class Nota implements Serializable {

	private static final long serialVersionUID = 1L;
	private String fcIdContenido;
	private String fcTitulo;
	private String fcDescripcion;
	private String fcLinkDetalle;
	private String fcIdCategoria;
	private String fcNombre;
	private String fcImgPrincipal;
	private String fiBanPatrocinio;
	private String fcIdTipoNota;
	private String fcDescripcionCategoria;
	private String fcNombreMagazine;
	private String fcFechaPublicacion;

	private String fiOrden;
	private String fcUrlExterna;

	public String getFcIdContenido() {
		return fcIdContenido;
	}

	public void setFcIdContenido(String fcIdContenido) {
		this.fcIdContenido = fcIdContenido;
	}

	public String getFcTitulo() {
		return fcTitulo;
	}

	public void setFcTitulo(String fcTitulo) {
		this.fcTitulo = fcTitulo;
	}

	public String getFcDescripcion() {
		return fcDescripcion;
	}

	public void setFcDescripcion(String fcDescripcion) {
		this.fcDescripcion = fcDescripcion;
	}

	public String getFcLinkDetalle() {
		return fcLinkDetalle;
	}

	public void setFcLinkDetalle(String fcLinkDetalle) {
		this.fcLinkDetalle = fcLinkDetalle;
	}

	public String getFcIdCategoria() {
		return fcIdCategoria;
	}

	public void setFcIdCategoria(String fcIdCategoria) {
		this.fcIdCategoria = fcIdCategoria;
	}

	public String getFcNombre() {
		return fcNombre;
	}

	public void setFcNombre(String fcNombre) {
		this.fcNombre = fcNombre;
	}

	public String getFcImgPrincipal() {
		return fcImgPrincipal;
	}

	public void setFcImgPrincipal(String fcImgPrincipal) {
		this.fcImgPrincipal = fcImgPrincipal;
	}

	public String getFiBanPatrocinio() {
		return fiBanPatrocinio;
	}

	public void setFiBanPatrocinio(String fiBanPatrocinio) {
		this.fiBanPatrocinio = fiBanPatrocinio;
	}

	public String getFcIdTipoNota() {
		return fcIdTipoNota;
	}

	public void setFcIdTipoNota(String fcIdTipoNota) {
		this.fcIdTipoNota = fcIdTipoNota;
	}

	public String getFcDescripcionCategoria() {
		return fcDescripcionCategoria;
	}

	public void setFcDescripcionCategoria(String fcDescripcionCategoria) {
		this.fcDescripcionCategoria = fcDescripcionCategoria;
	}

	public String getFcNombreMagazine() {
		return fcNombreMagazine;
	}

	public void setFcNombreMagazine(String fcNombreMagazine) {
		this.fcNombreMagazine = fcNombreMagazine;
	}

	public String getFcFechaPublicacion() {
		return fcFechaPublicacion;
	}

	public void setFcFechaPublicacion(String fcFechaPublicacion) {
		this.fcFechaPublicacion = fcFechaPublicacion;
	}

	public String getFiOrden() {
		return fiOrden;
	}

	public void setFiOrden(String fiOrden) {
		this.fiOrden = fiOrden;
	}

	public String getFcUrlExterna() {
		return fcUrlExterna;
	}

	public void setFcUrlExterna(String fcUrlExterna) {
		this.fcUrlExterna = fcUrlExterna;
	}

	@Override
	public String toString() {
		return "Nota [fcIdContenido=" + fcIdContenido + ", fcTitulo=" + fcTitulo + ", fcDescripcion=" + fcDescripcion
				+ ", fcLinkDetalle=" + fcLinkDetalle + ", fcIdCategoria=" + fcIdCategoria + ", fcNombre=" + fcNombre
				+ ", fcImgPrincipal=" + fcImgPrincipal + ", fiBanPatrocinio=" + fiBanPatrocinio + ", fcIdTipoNota="
				+ fcIdTipoNota + ", fcDescripcionCategoria=" + fcDescripcionCategoria + ", fcNombreMagazine="
				+ fcNombreMagazine + ", fcFechaPublicacion=" + fcFechaPublicacion + ", fiOrden=" + fiOrden
				+ ", fcUrlExterna=" + fcUrlExterna + "]";
	}

}
