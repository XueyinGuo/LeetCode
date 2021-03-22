package com.szu;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/12 13:19
 */


public class Test {
/*
* Finger interaction based on fingertip localization is a new way of human-computer interaction.
* Products based on finger interaction have appeared in the market in recent years, such as BBK learning machines,
* Xiaobawang learning machines and other smart eye series products.
* Finger Phonics is one of the classic applications in this type of product.
* Finger Phonics is the use of image processing and deep learning technology to recognize the text in the direction of
* the finger through the camera and output voice. In this paper I give a solution for the implementation of this application,
* i.e. using top-down cascading neural networks to achieve real-time fingertip localization, obtaining fingertip location
* information and then using traditional image processing methods to complete text segmentation, and finally combining
* OCR text recognition technology and text-to-speech engines to achieve finger-tap reading.
Compared with traditional fingertip localization algorithms, the deep learning-based approach overcomes many disadvantages
* such as different lighting, background color and brightness, and is much more robust. Therefore, we have done the
* following work and research on the fingertip localization algorithm in the Finger Phonics.
1.	We produced a partial hand dataset in point-and-speak scenarios and combined with the public dataset EgoGesture to
* complete the training of the cascade network.
2.	We trained the hand localization model using the MobileNet_SSD network structure, and optimized the network
* using hand features in the Finger Phonics scenario. After a comparative analysis of the network performance
* before and after the improvement, our proposed improvement strategy speeds up the network detection by 16.18%.
3.	For the fingertip localization network, we propose the YOLSE GHOST structure based on a fully convolutional
* neural network. The network uses GHOST Module to accelerate the computation and uses a channel-based attention
* mechanism to assist in improving the network detection accuracy. To verify the improvement of network performance
* by different ways of attention mechanisms, we have done several sets of comparative experiments. The experimental
* results show that the channel-based attention mechanism using SENET provides the greatest improvement in detection
* for both the full convolutional network and the GHOST Module.
* In the actual test results of the YOLSE GHOST network,
* the network accurately performed the fingertip positioning task with 90.49% accuracy, and had lower false alarm and missed detection rates.
4.	In the text segmentation stage, we propose the use of MSER combined with region fusion to segment the text.
* The performance test results in 100 frames show that the method accurately completes the text segmentation task with a speed of 20.63ms per frame.
Finally, we have combined the hand location network MobileNet_SSD,
* the fingertip location network YOLSE GHOST, the MSER text segmentation method and related interface
* libraries to implement a fingertip point-and-speak system that can detect English words in real time.


Key word: Human-computer interaction, Deep learning, Computer vison, Object detection, Finger Phonics

*
*
* */

}