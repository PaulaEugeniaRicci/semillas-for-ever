package ar.edu.unahur.obj2.semillas

interface Strategy {
    open fun seAsociaBien (parcela: Parcela, planta: Planta) : Boolean
}

class ParcelaEcologica : Strategy {
    override fun seAsociaBien (parcela: Parcela, planta: Planta) : Boolean {
        return (!parcela.tieneComplicaciones() && planta.esParcelaIdeal(parcela))
    }
}

class ParcelaIndustrial: Strategy {
    override fun seAsociaBien (parcela: Parcela, planta: Planta) : Boolean {
        return (planta.esFuerte() && parcela.listaPlantas.size == 2 )
    }
}

