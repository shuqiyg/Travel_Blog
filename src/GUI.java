/********************************************** 
Workshop # 8
Course: JAC433
Last Name:Yang
First Name:Shuqi
ID:132162207
Section:NBB 
This assignment represents my own work in accordance with Seneca Academic Policy. 
Signature 
Date:2022-03-28
**********************************************/ 

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.*; 
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class GUI extends Application{
	static TravelLog travelLog = new TravelLog();
	static ArrayList<String> countryNames = new ArrayList<String>();
	static ArrayList<String> cityNames = new ArrayList<String>();
	static ArrayList<Button> countryButtons = new ArrayList<Button>();
	static ArrayList<Button> cityButtons = new ArrayList<Button>();
	static HashMap<Button, GridPane> detailPanes = new HashMap<Button, GridPane>();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene mainMenu;
		Scene newLog;
		Scene selectCountry;
		Scene selectCity;
		Scene browseDetail;//for displaying each city's info
		
		GridPane mainPane = new GridPane();
		mainPane.setHgap(2);
		mainPane.setVgap(2);
		mainPane.setPadding(new Insets(10,20,10,15));
		
		GridPane newLogPane = new GridPane();
		newLogPane.setHgap(3);
		newLogPane.setVgap(3);
		newLogPane.setPadding(new Insets(15,23,15,20));
		
		GridPane countryPane = new GridPane();
		countryPane.setHgap(2);
		countryPane.setVgap(2);
		countryPane.setPadding(new Insets(10,20,10,15));
		
		GridPane cityPane = new GridPane();
		cityPane.setHgap(2);
		cityPane.setVgap(2);
		cityPane.setPadding(new Insets(10,20,10,15));
				
		GridPane imagePane = new GridPane();  
		imagePane.setHgap(2);   
		imagePane.setVgap(2);
		imagePane.setPadding(new Insets(10,20,10,15));
		
		GridPane detailPane = new GridPane();
		detailPane.setHgap(2);
		detailPane.setVgap(2);
		detailPane.setPadding(new Insets(10,20,10,15));	
		
		Button exit1 = new Button("EXIT");
		Button exit2 = new Button("EXIT");
		Button exitMain = new Button("Exit");
		exitMain.setMinHeight(50);
		exitMain.setMinWidth(300);	
		Button prevCountry = new Button("Previous");
		Button prevCity= new Button("Previous");
		Button prevImg = new Button("Previous");
		Button prevDetail = new Button("Previous");
		Button cancel = new Button("Cancel");
		Button delete = new Button("Delete");
		Button submit = new Button("Submit");
		Button filter = new Button("Sort By Rating");

		
		Button createLog = new Button("Create New Log");
		createLog.setMinWidth(300);
		createLog.setMinHeight(50);
		Button showLogs = new Button("Show Travel Logs");
		showLogs.setMinWidth(300);
		showLogs.setMinHeight(50);
		Button image = new Button("Show Images");
		image.setMinWidth(40);
		image.setMinHeight(20);
				
		Label title = new Label("Travel Dairy Logs");
		title.setPrefSize(200, 200);
		title.setStyle("-fx-text-fill: blue; -fx-font-size: 12pt");
		Label countryL = new Label("Country: ");
		Label cityL = new Label("City: ");
		Label fromL = new Label("Date from(yyyy-mm-dd): ");
		Label toL = new Label("Date to(dd/mm/yyyy-mm-dd): ");
		Label descL = new Label("Description: ");
		Label rateL = new Label("Rating(Integer 1 to 5): ");
		Label urlL = new Label("Image URL(Optional): ");
		
		//Travel Log City Info Labels for detailPane
		Label nameC = new Label();
		Label descC = new Label();
		Label fromC = new Label();
		Label toC = new Label();
		Label ratingC = new Label();
		
		TextField countryTF = new TextField();
		countryTF.textProperty().addListener((observable, oldValue, newValue) ->{
			String country = countryTF.getText();
		});
		TextField cityTF = new TextField();
		TextField fromTF = new TextField();
		TextField toTF = new TextField();
		TextArea descTF = new TextArea();
		descTF.setPrefSize(200, 100);
		TextField rateTF = new TextField();
		TextField urlTF = new TextField();
		
		Alert alert1 = new Alert(AlertType.ERROR);
		Alert alert2 = new Alert(AlertType.INFORMATION);		
		
 		mainPane.add(title, 8, 0);
 		mainPane.add(createLog, 8, 5);
 		mainPane.add(showLogs, 8, 10);
 		//mainPane.add(images, 8, 15);
 		mainPane.add(exitMain, 8, 20);
		mainMenu = new Scene(mainPane, 400,300);
		
		newLogPane.add(countryL, 3, 3);
		newLogPane.add(cityL, 3, 6);
		newLogPane.add(fromL, 3, 9);
		newLogPane.add(toL, 3, 12);
		newLogPane.add(descL, 3, 15);
		newLogPane.add(urlL, 3, 18);
		newLogPane.add(rateL, 3, 21);
		newLogPane.add(countryTF, 6, 3);
		newLogPane.add(cityTF, 6, 6);
		newLogPane.add(fromTF, 6, 9);
		newLogPane.add(toTF, 6, 12);
		newLogPane.add(descTF, 6, 15);
		newLogPane.add(urlTF, 6, 18);
		newLogPane.add(rateTF, 6, 21);
		newLogPane.add(submit, 3, 24);
		newLogPane.add(cancel, 8, 24);
		newLog = new Scene(newLogPane, 600,400);

		countryPane.add(prevCountry, 3, 30);
		countryPane.add(exit1, 10, 30);
		selectCountry = new Scene(countryPane, 400,300);
				
		//cityPane.add(prevCity, 3, 30);
		//cityPane.add(exit2, 10, 30);
		//cityPane.add(filter, 20, 0);
 		selectCity = new Scene(cityPane, 400, 300);
		GridPane gp = new GridPane();
		gp.add(prevDetail, 3, 30);
		gp.add(delete, 6, 30);
		detailPane.getChildren().add(gp);
		browseDetail = new Scene(detailPane, 400, 300);
		
		createLog.setOnAction(e->{
			countryTF.setText("");
			cityTF.setText("");
			fromTF.setText("");
			toTF.setText("");
			descTF.setText("");
			urlTF.setText("");
			rateTF.setText("");
			primaryStage.setScene(newLog);
			System.out.println(e);
		});
		
		showLogs.setOnAction(e0->{	
			int countryButtonHeight = 2;
			countryPane.getChildren().clear();
			countryPane.add(prevCountry, 3, 30);
			countryPane.add(exit1, 10, 30);
			for(Button cb : countryButtons) {
				countryPane.add(cb, 5, countryButtonHeight);
				countryButtonHeight+=5;
				cb.setOnAction(e1->{
					int cityButtonHeight = 2;
					cityPane.getChildren().clear();
					cityPane.add(prevCity, 3, 40);
					cityPane.add(exit2, 10, 40);
					cityPane.add(filter, 30, 0);
					for(Country c: travelLog.countries) {
						if(cb.getId().equals(c.name)) {
							for(City cy : c.cities) {
								for(Button ctyButton : cityButtons) {
									if(ctyButton.getId().equals(cy.name)) {
										cityPane.add(ctyButton, 5, cityButtonHeight);
										cityButtonHeight+=5;
									}
								}								
							}
						}
					}
					primaryStage.setScene(selectCity);
				});
			}
			primaryStage.setScene(selectCountry);
			System.out.println(e0);
		});
		
		submit.setOnAction(e->{
			int heightCountry = 1;
			String countryInput = countryTF.getText();
			String cityInput = cityTF.getText();
			String fromInput = fromTF.getText();
			String toInput = toTF.getText();
			String descInput = descTF.getText();
			String imgURLInput = urlTF.getText();
			String ratingInput = rateTF.getText();
			LocalDate fromDate = LocalDate.parse(fromInput);
			LocalDate toDate = LocalDate.parse(toInput);
			int rating = Integer.parseInt(ratingInput);
			
			if(cityNames.contains(cityInput)) {
				alert1.setContentText("City already exists, please enter a new city for your Log");
				countryTF.setText("");
				cityTF.setText("");
				fromTF.setText("");
				toTF.setText("");
				descTF.setText("");
				urlTF.setText("");
				rateTF.setText("");
				alert1.show();
			}else {
				cityNames.add(cityInput);
				// new city button
				Button newCYB = new Button(cityInput);
				newCYB.setId(cityInput);
				newCYB.setMinWidth(100);
				newCYB.setMinHeight(30);
				
				// new city detail info 
				City newCity = new City(cityInput, descInput, fromDate, toDate, rating);

				nameC.setText(cityInput);
				descC.setText(descInput);
				fromC.setText(fromInput);
				toC.setText(toInput);
				ratingC.setText(ratingInput);
				GridPane cityInfoPane = new GridPane();
				cityInfoPane.setHgap(2);   
				cityInfoPane.setVgap(2);
				cityInfoPane.setPadding(new Insets(10,20,10,15));
				//separate url input
				if(imgURLInput != null) {
					newCity.addImage(imgURLInput);
					//imgURLs.add(imgURLInput);
					image.setId(cityInput);
					cityInfoPane.add(image, 5, 40);
				}
				//show image when button's clicked
				image.setOnAction(e3->{
					  try {
						  FileInputStream fileInput  = new FileInputStream(newCity.imageURL);
						 Image cityImage = new Image(fileInput);
					        ImageView imageView = new ImageView(cityImage);
					        imageView.setFitHeight(400);
					        imageView.setFitWidth(400);
					        imageView.setPreserveRatio(true);
					        Button exit = new Button("Back");
					        exit.setOnAction(e4->{
					        	primaryStage.setScene(selectCity);
					        });
					        VBox vbox = new VBox(imageView);
							vbox.setPadding(new Insets(10,20,10,15));
					        vbox.getChildren().add(exit);					    
					        Scene scene = new Scene(vbox, 600, 400);
					        primaryStage.setScene(scene);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}	       
				});
				//cityInfoPane.getChildren().clear();
				Label cityName = new Label("Name: ");
				Label travelDetail = new Label("Travel Detail: ");
				Label from = new Label("Date from: ");
				Label to = new Label("Date to: ");
				Label rt = new Label("Rating: ");
				cityInfoPane.add(cityName, 5,5);
				cityInfoPane.add(travelDetail, 5,10);
				cityInfoPane.add(from,5,15);
				cityInfoPane.add(to,5,20);
				cityInfoPane.add(rt, 5, 25);
				cityInfoPane.add(nameC, 10, 5);
				cityInfoPane.add(descC,10,10);
				cityInfoPane.add(fromC,10,15);
				cityInfoPane.add(toC, 10, 20);
				cityInfoPane.add(ratingC, 10,25);
				prevDetail.setId(cityInput);
				cityInfoPane.add(prevDetail,5,50);
				delete.setId(cityInput);
				cityInfoPane.add(delete,10,50);
				//detailPanes.put(cityInput, cityInfoPane);

				newCYB.setOnAction(event->{
					detailPane.getChildren().clear();
					detailPane.add(detailPanes.get(newCYB),5,5);
					primaryStage.setScene(browseDetail);
					System.out.println(detailPane);
				});
				cityButtons.add(newCYB);
				detailPanes.put(newCYB, cityInfoPane);
				//System.out.println(detailPanes);
				
				//country button
				if(countryNames.contains(countryInput)){
					alert2.setContentText("Country already exists, creating new city for the travel log");
					alert2.show();
					travelLog.countries.forEach(c->{
						if(c.name.equals(countryInput)) {
							c.addCity(newCity);
						}
					});
				}else {
					countryNames.add(countryInput);
					Country newCountry = new Country(countryInput);
					newCountry.addCity(newCity);
					travelLog.countries.add(newCountry);
					Button newCB = new Button(countryInput);
					newCB.setId(countryInput);
					newCB.setMinWidth(200);
					newCB.setMinHeight(50);
					countryPane.add(newCB, 1, heightCountry);
					countryButtons.add(newCB);
				}
				primaryStage.setScene(mainMenu);
				System.out.println(e);
			}		
		});
		 
		exit1.setOnAction(e->{
			primaryStage.close();
			System.out.println(e);
		});
		
		exit2.setOnAction(e->{
			primaryStage.close();
			System.out.println(e);
		});
		
		exitMain.setOnAction(e->{
			primaryStage.close();
			System.out.println(e);
		});
		
		cancel.setOnAction(e->{
			primaryStage.setScene(mainMenu);
			System.out.println(e);
		});
		
		prevCountry.setOnAction(e->{
			primaryStage.setScene(mainMenu);
			System.out.println(e);
		});
		
		prevCity.setOnAction(e->{
			primaryStage.setScene(selectCountry);
			System.out.println(e);
		});
			

//		for(Button ctyB: cityButtons) {
//			ctyB.setOnAction(act->{
//				//GridPane dPane = detailPanes.get(ctyB.getId());
//				Scene bDetail = new Scene(detailPanes.get(ctyB.getId()));
//				primaryStage.setScene(bDetail);
//			});
//		}
		
		delete.setOnAction(e->{
			for(Country c : travelLog.countries) {
				for(City cty: c.cities) {
					if(delete.getId().equals(cty.name)) {
						cityNames.remove(cty.name);
						c.cities.remove(cty);	
						for(Button bt: cityButtons) {
							if(bt.getId().equals(delete.getId())) {
								cityButtons.remove(bt);
								break;
							}
						}
						detailPanes.remove(delete.getId());
						break;
					}
				}
			}
			primaryStage.setScene(selectCountry);
			System.out.println(e);
		});
		prevImg.setOnAction(e->{
			primaryStage.setScene(mainMenu);
			System.out.println(e);
		});
		prevDetail.setOnAction(e->{
			//detailPanes.get(prevDetail.getId()).getChildren().clear();
			primaryStage.setScene(selectCity);
			System.out.println(e);
		});
//		for(Button b: cityButtons) {
//			b.setOnAction(event->{
//				//if(detailPane.getChildren() != null)
//				detailPane.getChildren().clear();
//				//detailPane.add(prevDetail,5,50);
//				detailPane.add(detailPanes.get(b.getId()),5,5);
//				primaryStage.setScene(browseDetail);
//			});
//		}

		primaryStage.setMinWidth(500);
		primaryStage.setMinHeight(500);
		primaryStage.setTitle("Travel Dairy");
		primaryStage.setScene(mainMenu);
		primaryStage.show();
	}

}
