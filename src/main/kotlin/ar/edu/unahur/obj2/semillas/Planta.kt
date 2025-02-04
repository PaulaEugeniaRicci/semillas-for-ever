package ar.edu.unahur.obj2.semillas

/*Kotlin: The open annotation on a class is the opposite of Java's final:
it allows others to inherit from this class. By default, all classes in Kotlin are final.
Open CAN be subclassed, abstract MUST be subclassed- Abstract can not be instantiated */

abstract class Planta(var altura: Double, val anioObtencion: Int) {
    open fun toleranciaAlSol() = 7
    fun esFuerte(): Boolean = this.toleranciaAlSol() > 9
    open fun daSemillas(): Boolean = this.esFuerte()
    abstract fun esParcelaIdeal(parcela: Parcela): Boolean
}

open class Menta(altura: Double, anioObtencion: Int) : Planta(altura, anioObtencion) {
    override fun daSemillas(): Boolean {
        return super.daSemillas() || this.altura > 0.4
    }
    open fun espacioOcupado(): Double = this.altura + 1
    override fun esParcelaIdeal(parcela: Parcela): Boolean = parcela.superficie() > 6
}

open class Soja(altura: Double, anioObtencion: Int) : Planta (altura, anioObtencion) {
    override fun toleranciaAlSol(): Int {
        if (this.altura < 0.5) return 6
        else if (this.altura <= 1) return 8
        else return 12
    }
    override fun daSemillas(): Boolean {
        return super.daSemillas() || this.anioObtencion < 2007 && (this.altura < 0.9 && this.altura > 0.75)
    }
    fun espacioOcupado(): Double = this.altura / 2
    override fun esParcelaIdeal(parcela: Parcela): Boolean = parcela.horasSol == this.toleranciaAlSol()
}

class Quinoa(altura: Double, anioObtencion: Int, var espacioOcupado: Double) : Planta (altura, anioObtencion) {
    override fun toleranciaAlSol(): Int {
        if (this.espacioOcupado < 0.3) return 10
        else return super.toleranciaAlSol()
    }
    override fun daSemillas(): Boolean {
        return super.daSemillas() || (this.anioObtencion in 2002..2007)
    }
    override fun esParcelaIdeal(parcela: Parcela): Boolean = parcela.listaPlantas.all{ p : Planta -> p.altura < 1.5 }
}

class SojaTransgenica (altura: Double, anioObtencion: Int) : Soja (altura, anioObtencion){
    override fun daSemillas(): Boolean = false
    override fun esParcelaIdeal(parcela: Parcela): Boolean = parcela.cantidadMaxima() == 1
}

class Peperina (altura: Double, anioObtencion: Int) : Menta (altura, anioObtencion){
    override fun espacioOcupado(): Double = super.espacioOcupado() * 2
}

