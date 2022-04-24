package ar.edu.unahur.obj2.semillas

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class IntaTest : DescribeSpec ({
    describe("Prueba de INTA ") {
        val inta = Inta
        val soja = Soja(1.5, 2021)
        val menta = Menta(1.5, 2021)
        val parcela = ParcelaEcologica(20.0, 1.0, 10, mutableListOf(soja, soja, soja, soja))
        val parcelaPequena = ParcelaIndustrial(2.0, 1.0, 5, mutableListOf(menta, soja))

        inta.agregarParcela(parcela)
        inta.agregarParcela(parcela)

        it ("Prueba de parcelas agregadas "){
            inta.coleccionParcelas.shouldContainExactly(parcela, parcela)
        }
        it ("Prueba de promedio de plantas por parcela"){
            inta.calcularPromedio().shouldBe( 4)
        }
        it ("Prueba de promedio con resultados truncados"){
            inta.agregarParcela(parcelaPequena)
            inta.calcularPromedio().shouldBe(3)
        }
        it ("Prueba de parcela mas autosustentable"){
            inta.parcelaMasAutosustentable().shouldBe(null)
        }
    }
})