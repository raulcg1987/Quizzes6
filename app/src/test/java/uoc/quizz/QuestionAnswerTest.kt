package uoc.quizz

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class QuestionAnswerTest {

    val questions = listOf<pregunta>(
        pregunta("¿Quién inventó el teléfono?", listOf("Thomas Edison","Graham Bell"), 2,"https://upload.wikimedia.org/wikipedia/commons/1/10/Alexander_Graham_Bell.jpg",1),
        pregunta("¿Quién desarrollo la teoría de la relatividad?", listOf("Albert Einstein","Isaac Newton",""), 1,"https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Albert_Einstein_Head.jpg/1200px-Albert_Einstein_Head.jpg",2 ),
        pregunta("¿Quién fue Confucio?", listOf("En la antigua china, fue un chino-japonés que inventó la confusión.","Pensador chino fundador de la escuela filosófica conocida como confucianismo",""), 2,"https://lamenteesmaravillosa.com/wp-content/uploads/2016/08/confucio-2.jpg",3 )
    )

    @Test
    fun question1IsCorrect() {
        Assert.assertEquals(2, this.questions.get(0).getRigthChoice())
    }
    @Test
    fun question2IsCorrect() {
        Assert.assertEquals(1, this.questions.get(1).getRigthChoice())
    }
    @Test
    fun question3IsCorrect() {
        Assert.assertEquals(2, this.questions.get(2).getRigthChoice())
    }
}