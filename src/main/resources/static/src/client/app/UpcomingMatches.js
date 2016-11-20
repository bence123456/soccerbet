import React from 'react';
import {List, ListItem} from 'material-ui/List';
import Avatar from 'material-ui/Avatar';
import Divider from 'material-ui/Divider';
import MobileTearSheet from './MobileTearSheet';
import TextField from 'material-ui/TextField';
import {Card, CardActions, CardHeader, CardMedia, CardTitle, CardText} from 'material-ui/Card';
import FlatButton from 'material-ui/FlatButton';
import Tooltip from 'material-ui/internal/Tooltip';
import Dialog from 'material-ui/Dialog';
import {Link} from 'react-router'

const leftMarginStyle = {
	marginLeft: '100px'
};

const blackStyle = {
	color: 'black'
};

const errorStyle = {
	color: 'red'
};

class UpcomingMatches extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
		    matches: [],
		    goals: [],
		    betButtonDisabled: false,
		    dateErrorText: 'Sajnos a mérkőzések már elkezdődtek, így a tippelés nem lehetséges!',
		    showTooltip: false,
		    dialogOpen: false,
		    errorText: '',
		    value: props.value
		}
        this.onChange = this.onChange.bind(this);
	}

    onGoalsChange = (index, event) => {
        var tmpGoals = this.state.goals;
        tmpGoals[index] = event.target.value;
        this.setState({goals: tmpGoals });
        this.onChange(event);
    }

    onChange(event) {
        var bet = event.target.value;
        if (bet != "" && !isNaN(bet) && Number(bet) >= 0 && Number(bet) < 100) {
            this.setState({ errorText: '',  betButtonDisabled: false})
        } else {
            this.setState({ errorText: 'Nincs minden mező 0 és 99 közötti számmal kitöltve!',  betButtonDisabled: true })
        }
    }

    onMouseEnter() {
        var matchesStarted = this.state.matches[0].dateTime < new Date().toJSON();
        if (matchesStarted) {
            this.setState({showTooltip: true, betButtonDisabled: true});
        } else {
            this.setState({showTooltip: false});
        }
    }

    onMouseLeave() {
        this.setState({showTooltip: false});
    }

    onClick() {
        var saveLink = getSaveLink(this);

        fetch(saveLink).then((response) => { return response.json() }).then( (json) => {
            if (json.success) {
                window.location.reload();
            } else {
                this.handleOpen();
            }
        });
    }

    handleOpen = () => {
        this.setState({dialogOpen: true});
    };

    handleClose = () => {
        this.setState({dialogOpen: false});
    };

	componentDidMount() {
        fetch(window.backendHost + '/api/matches/search/findByStatus?status=SCHEDULED')
        .then((response) => { return response.json() })
        .then( (json) => {
            this.setState({matches: json._embedded.matches});

            var initGoals = [];
            for(var i=0; i<json._embedded.matches.length*2; i++) {
                initGoals.push("0");
            }
            this.setState({goals: initGoals});
        });
	}

	render() {
        var matchNodes = this.state.matches.map(function (match, i) {
			var homeTeamName = match.homeTeamName;
			var awayTeamName = match.awayTeamName;
			var homeTeamLogoSrc = "/images/logos/" + homeTeamName + ".png";
			var awayTeamLogoSrc = "/images/logos/" + awayTeamName + ".png";
			var awayIndex = this.state.matches.length + i;

            return (
				<List >
					<ListItem style={blackStyle}
						primaryText = {homeTeamName}
						rightAvatar = { <Avatar> <TextField value={this.state.goals[i]} underlineShow={false} inputStyle={{textAlign:'center'}} style={{width: '20px'}}
						        hintStyle={{textAlign:'center', width: '20px'}} hintText="0" onChange={this.onGoalsChange.bind(this, i)} /> </Avatar> }
						leftAvatar = {<Avatar src = {homeTeamLogoSrc} />} />
					<ListItem style={blackStyle}
						primaryText = {awayTeamName}
						rightAvatar = { <Avatar> <TextField value={this.state.goals[awayIndex]} underlineShow={false} inputStyle={{textAlign:'center'}} style={{width: '20px'}}
						        hintStyle={{textAlign:'center', width: '20px'}} hintText="0" onChange={this.onGoalsChange.bind(this, awayIndex)} /> </Avatar> }
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
            if (i == 0) {
                var dateTime = match.dateTime.replace("T"," ").substring(0,16);
                var num = Number(dateTime.substring(12,13)) + 1;
                return dateTime.substr(0, 12) + num + dateTime.substr(13);
            } else {
                return "";
            }
        });

        if (this.state.matches.length >= 0) {
            var saveLink = getSaveLink(this);
            const actions = [
                <FlatButton label="Rendben" primary={true} onTouchTap={this.handleClose} />
            ];

            return (
			    <div style={{display: 'flex', flexWrap: 'wrap', marginLeft: '145px'}}>
                    <Card style={{backgroundColor:'#E0E0E0', marginTop: '24px', height: '520px', width: '520px'}} >
                        <CardHeader style={{fontWeight: 'bold'}} title="Tippeld meg az aktuális forduló párosításainak eredményeit!" />
                        <CardText style={blackStyle}> Minden mérkőzés kezdési időpontja: {times[0]} </CardText>
                        <CardText style={blackStyle}>
                            <p style={{fontStyle: 'italic'}}> Néhány fontos tudnivalő a tippek leadása előtt: </p>
                                 <p/> 1: Tippelés megadásához írd be a csapatok mellett található szürke körbe az általad gondolt gólok számát!
                                 <p/> 2: Egy felhasználónak egy meccshez csak egy tippje lehet. Az új tippek mindig felülírják a régit!
                                 <p/> 3: Kizárólag 0 és 99 közötti számokat lehet megadni!
                                 <p/> 4: Minden mérkőzésre érvényes értéket kell beírni a mérkőzés kezdetéig, meccsenkénti tippelés nem lehetséges!
                        </CardText>
                        <CardText style={errorStyle}>
                            {this.state.errorText}
                        </CardText>
                        <CardActions >
                            <Tooltip show={this.state.showTooltip} style={{color: 'white'}} label={this.state.dateErrorText} horizontalPosition="right" verticalPosition="bottom" />
                            <FlatButton label="Tippek mentése" disabled={this.state.betButtonDisabled}  onMouseEnter={this.onMouseEnter.bind(this)}
                                onMouseLeave={this.onMouseLeave.bind(this)} onClick={this.onClick.bind(this)} />
                                    <Dialog title="Hiba a mentés során!" actions={actions} modal={false} open={this.state.dialogOpen} onRequestClose={this.handleClose} >
                                      Hiba történt az adatok lementése során. Kérlek próbálkozz újra!
                                    </Dialog>
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

function getSaveLink(page) {
    var matchIds = "";
    for (var i=0; i<page.state.matches.length; i++) {
        matchIds += page.state.matches[i].id + ",";
    }

    var homeGoals = "";
    var awayGoals = "";
    for(var j=0; j<page.state.goals.length; j++) {
        if (page.state.matches.length <= j) {
            awayGoals += page.state.goals[j] + ",";
        } else {
            homeGoals += page.state.goals[j] + ",";
        }
    }

    return window.backendHost + "/bet/create?userId=" + window.account.id + "&matchIds=" + matchIds + "&homeGoals=" + homeGoals + "&awayGoals=" + awayGoals;
}

export default UpcomingMatches;