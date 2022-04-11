package ar.edu.unahur.obj2.semillas

class Parcela (var ancho: Double, var largo: Double, var horasSol: Int, var plantas: MutableList<Planta>){
    fun superficie() : Double = ancho * largo
    fun cantidadMaxima() : Int {
        if (ancho > largo) { return ( this.superficie() /5 ).toInt() }
        else { return (this.superficie() / 3 + largo).toInt() }
    }
}