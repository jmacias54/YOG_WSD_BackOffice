/**
 * @author Jesus Armando Macias Benitez
 */
package mx.com.amx.wsd.yog.backoffice.model;

/**
 * @author Jesus Armando Macias Benitez
 *
 */
public class NotaMagazine {

	private String idMagazine;
	private String idContenido;
	private String urlExterna;
	private int orden;

	public String getIdMagazine() {
		return idMagazine;
	}

	public void setIdMagazine(String idMagazine) {
		this.idMagazine = idMagazine;
	}

	public String getIdContenido() {
		return idContenido;
	}

	public void setIdContenido(String idContenido) {
		this.idContenido = idContenido;
	}

	public String getUrlExterna() {
		return urlExterna;
	}

	public void setUrlExterna(String urlExterna) {
		this.urlExterna = urlExterna;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	@Override
	public String toString() {
		return "NotaMagazine [idMagazine=" + idMagazine + ", idContenido=" + idContenido + ", urlExterna=" + urlExterna
				+ ", orden=" + orden + "]";
	}

}
