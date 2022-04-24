package ar.edu.unahur.obj2.semillas

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.doubles.shouldBeBetween
import io.kotest.matchers.shouldBe

class IntaTest : DescribeSpec ({
    describe("Prueba de estadisticas INTA") {
        val soja = Soja(1.5, 2021)
        val menta = Menta(1.5, 2021)
        val parcela = ParcelaEcologica(20.0, 1.0, 10, mutableListOf(soja, soja, soja, soja))
        val parcelaPequena = ParcelaIndustrial(2.0, 1.0, 5, mutableListOf(menta, soja))

        it ("Promedio de 0 plantas en 0 parcelas"){     // Empezar por caso base: 0, empty, null
            Inta.calcularPromedio().shouldBe( 0)
        }
        it ("Verificar parcelas agregadas al INTA"){
            Inta.agregarParcela(parcela)
            Inta.agregarParcela(parcela)
            Inta.coleccionParcelas.shouldContainExactly(parcela, parcela)
        }
        it ("Promedio de 8 plantas en 2 parcelas"){
            Inta.calcularPromedio().shouldBe( 4)
        }
        it ("Promedio de 0 plantas en una parcela"){
            Inta.coleccionParcelas.clear()
            parcelaPequena.listaPlantas.clear()
            Inta.agregarParcela(parcelaPequena)
            Inta.calcularPromedio().shouldBe(0)
        }
    }
    describe("Parcela mas autosustentable"){
        Inta.coleccionParcelas.clear()
        val menta = Menta(1.4, 2020)
        val peperina = Peperina(1.3, 2019)
        val quinoa = Quinoa (1.2, 2018, 12.0)
        val soja = Soja(1.5, 2021)
        val parcela = ParcelaEcologica(20.0, 1.0, 6, mutableListOf(menta, menta, peperina, quinoa, quinoa))
        val parcela2 = ParcelaEcologica(20.0, 1.0, 6, mutableListOf(menta, menta, peperina, soja, soja))
        val parcela3 = ParcelaEcologica(20.0, 1.0, 6, mutableListOf(menta, quinoa, quinoa, quinoa, peperina, peperina, soja, soja))
        val parcelaPequena = ParcelaIndustrial(2.0, 1.0, 5, mutableListOf(menta, soja))

        it ("No hay parcelas agregadas al INTA"){
            Inta.parcelaMasAutosustentable().shouldBe(null)
        }
        it ("Hay parcelas agregadas pero ninguna es autosustentable"){
            Inta.coleccionParcelas.add(parcelaPequena)
            Inta.parcelaMasAutosustentable().shouldBe(null)
        }
        it ("La parcela mas autosustentable es la unica que hay"){
            Inta.coleccionParcelas.clear()
            Inta.coleccionParcelas.add(parcela)
            Inta.parcelaMasAutosustentable().shouldBe(parcela)
        }
        it ("La parcela mas autosustentable entre varias parcelas"){
            Inta.coleccionParcelas.clear()
            Inta.coleccionParcelas.add(parcelaPequena)
            Inta.coleccionParcelas.add(parcela)
            Inta.coleccionParcelas.add(parcela2)
            Inta.coleccionParcelas.add(parcela3)
            Inta.parcelaMasAutosustentable().shouldBe(parcela)
        }
        it ("Porcentaje de plantas bien asociadas de parcela mas autosustentable"){
            Inta.coleccionParcelas.add(parcelaPequena)
            Inta.coleccionParcelas.add(parcela2)
            Inta.coleccionParcelas.add(parcela3)
            val masSustentable = Inta.parcelaMasAutosustentable()
            masSustentable?.porcentajeAsociadas().shouldBe(60.0)
            // masSustentable.porcentajeAsociadas().shouldBeBetween(1.0, 2.0, 0.1)
        }
    }
})