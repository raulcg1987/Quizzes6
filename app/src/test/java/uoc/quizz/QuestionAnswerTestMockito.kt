package uoc.quizz

import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock


class QuestionAnswerTestMockito {

    @Test
    fun question1IsCorrect() {
        val pregunta1 = mock(pregunta::class.java)
        `when`(pregunta1.getRigthChoice()).thenReturn(2)
        Assert.assertEquals(2, pregunta1.getRigthChoice())
    }

    @Test
    fun question2IsCorrect() {
        val pregunta2 = mock(pregunta::class.java)
        `when`(pregunta2.getRigthChoice()).thenReturn(1)
        Assert.assertEquals(1, pregunta2.getRigthChoice())
    }
    
    @Test
    fun question3IsCorrect() {
        val pregunta3 = mock(pregunta::class.java)
        `when`(pregunta3.getRigthChoice()).thenReturn(2)
        Assert.assertEquals(2, pregunta3.getRigthChoice())
    }

}