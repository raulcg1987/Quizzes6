package uoc.quizz

//Class constructor
class pregunta(titulo: String, respuestas: List<String>, resultado: Int, imagen: String, numpregunta: Int) {
    var titulo: String = titulo
    var respuestas: List<String> = respuestas
    var resultado: Int = resultado
    var imagen: String = imagen
    var numpregunta: Int = numpregunta

    fun getRigthChoice(): Int {
        return resultado
    }



}

