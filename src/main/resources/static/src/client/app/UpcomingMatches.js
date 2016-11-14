import React from 'react';
import {List, ListItem} from 'material-ui/List';
import Avatar from 'material-ui/Avatar';
import Divider from 'material-ui/Divider';
import MobileTearSheet from './MobileTearSheet';
import TextField from 'material-ui/TextField';
import {Card, CardActions, CardHeader, CardMedia, CardTitle, CardText} from 'material-ui/Card';
import FlatButton from 'material-ui/FlatButton';
import {Link} from 'react-router'

const leftMarginStyle = {
	marginLeft: '100px'
};

const blackStyle = {
	color: 'black'
};

class UpcomingMatches extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
		    matches: [],
		    homeGoals: [],
		    awayGoals: [],
		    betButtonDisabled: false,
		    errorText: '',
		    value: props.value
		}
        this.onChange = this.onChange.bind(this);
	}

    onHomeGoalsChange = (index, event) => {
        var tmpHomeGoals = this.state.homeGoals;
        tmpHomeGoals[index] = event.target.value;
        this.setState({homeGoals: tmpHomeGoals });
        this.onChange(event);
    }

    onAwayGoalsChange = (index, event) => {
        var tmpAwayGoals = this.state.awayGoals;
        tmpAwayGoals[index] = event.target.value;
        this.setState({awayGoals: tmpAwayGoals });
        this.onChange(event);
    }

    onChange(event) {
        var bet = event.target.value;
        if (bet != "" && !isNaN(bet) && Number(bet) >= 0 && Number(bet) < 100) {
            this.setState({ errorText: '',  betButtonDisabled: false})
        } else {
            this.setState({ errorText: 'Kérlek ellenőrizd, hogy minden mező ki van-e töltve érvényes, 0 és 99 közötti számmal!',  betButtonDisabled: true })
        }
    }

	componentDidMount() {
        fetch(window.backendHost + '/api/matches/search/findByStatus?status=SCHEDULED')
        .then((response) => { return response.json() })
        .then( (json) => {
            this.setState({matches: json._embedded.matches});


        });

        var initGoals = ["0", "0", "0", "0", "0", "0", "0", "0"];
        this.setState({homeGoals: initGoals, awayGoals: initGoals });
	}

	render() {
        var matchNodes = this.state.matches.map(function (match, i) {
			var homeTeamName = match.homeTeamName;
			var awayTeamName = match.awayTeamName;
			var homeTeamLogoSrc = "/images/logos/" + homeTeamName + ".png";
			var awayTeamLogoSrc = "/images/logos/" + awayTeamName + ".png";

            return (
				<List >
					<ListItem style={blackStyle}
						primaryText = {homeTeamName}
						rightAvatar = { <Avatar> <TextField value={this.state.homeGoals[i]} underlineShow={false} inputStyle={{textAlign:'center'}} style={{width: '20px'}}
						        hintStyle={{textAlign:'center', width: '20px'}} hintText="0" onChange={this.onHomeGoalsChange.bind(this, i)} /> </Avatar> }
						leftAvatar = {<Avatar src = {homeTeamLogoSrc} />} />
					<ListItem style={blackStyle}
						primaryText = {awayTeamName}
						rightAvatar = { <Avatar> <TextField value={this.state.awayGoals[i]} underlineShow={false} inputStyle={{textAlign:'center'}} style={{width: '20px'}}
						        hintStyle={{textAlign:'center', width: '20px'}} hintText="0" onChange={this.onAwayGoalsChange.bind(this, i)} /> </Avatar> }
						leftAvatar = {<Avatar src = {awayTeamLogoSrc} />} />
				</List>
            );
        }, this);

		var mobileTearSheets = matchNodes.map(function (matchNode, i) {
			var sheets = [];
				if (i % 4 == 0) {
					const sheet = (
						<MobileTearSheet key={i}>
							{matchNodes[i]}
							<Divider/>
							{matchNodes[i+1]}
							<Divider/>
							{matchNodes[i+2]}
							<Divider/>
							{matchNodes[i+3]}
						</MobileTearSheet>
					);
					sheets.push(sheet);
				}

			return sheets;
		});

        var times = this.state.matches.map(function (match, i) {
            return match.dateTime.replace("T"," ").substring(0,16);
        });

        if (this.state.matches.length >= 0) {
            var matchIds = "";
            for (var i=0; i<this.state.matches.length; i++) {
                var selfLink = this.state.matches[i]._links.self.href;
                var lastIndexOfBackslash = selfLink.lastIndexOf('/');
                matchIds += selfLink.substring(lastIndexOfBackslash + 1) + ",";
            }

            var homeGoals = "";
            for(var j=0; j<this.state.homeGoals.length; j++) {
                homeGoals += this.state.homeGoals[j] + ",";
            }

            var awayGoals = "";
            for(var k=0; k<this.state.awayGoals.length; k++) {
                awayGoals += this.state.awayGoals[k] + ",";
            }

            var saveLink = "http://localhost:8080/bet/create?userId=" + window.account.id + "&matchIds=" + matchIds + "&homeGoals=" + homeGoals + "&awayGoals=" + awayGoals

            return (
			    <div style={{display: 'flex', flexWrap: 'wrap', marginLeft: '145px'}}>
                    <Card style={{backgroundColor:'#E0E0E0', marginTop: '24px', height: '520px', width: '520px'}} >
                        <CardHeader style={{fontWeight: 'bold'}} title="Tippeld meg az aktuális forduló párosításainak eredményeit!" />
                        <CardText style={blackStyle}> Minden mérkőzés kezdési időpontja: {times[0]} </CardText>
                        <CardText style={blackStyle}>
                            <p style={{fontStyle: 'italic'}}> Néhány fontos tudnivalő a tippek leadása előtt: </p>
                                 <p/> 1: Tippelés megadásához írd be a csapatok mellett található szürke körbe az általad gondolt gólok számát!
                                 <p/> 2: Egy felhasználó csak egyszer tippelhet!
                                 <p/> 3: Kizárólag 0 és 99 közötti számokat lehet megadni!
                                 <p/> 4: Minden mérkőzésre érvényes értéket kell beírni a mérkőzés kezdetéig, meccsenkénti tippelés nem lehetséges!
                                 <p/> 5: Az alapértelmezett érték minden esetben a 0!
                        </CardText>
                        <CardText style={{color: 'red'}}>
                            {this.state.errorText}
                        </CardText>
                        <CardActions>
                            <FlatButton label="Tippek mentése" disabled={this.state.betButtonDisabled} href={saveLink} />
                        </CardActions>
                    </Card>
                    {mobileTearSheets}
			    </div>
            );
        } else {
			return (
                <div>
                    <h4 style={leftMarginStyle}>
                        Jelenleg nincs kiírt mérkőzés a német kupában, kérlek nézz vissza később!
                    </h4>
                    <FlatButton label="Vissza a kezdőlapra" containerElement={<Link to="/"/>} backgroundColor= "white" style={leftMarginStyle} />
                </div>
			);
		}
	}
}

export default UpcomingMatches;